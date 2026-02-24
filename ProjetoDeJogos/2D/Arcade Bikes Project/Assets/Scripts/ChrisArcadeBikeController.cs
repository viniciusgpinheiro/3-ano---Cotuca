using UnityEngine;
using System.Collections;
using DG.Tweening;


public class ChrisArcadeBikeController : MonoBehaviour
{

	public enum BikeArcadeLifes
	{
		Left = 7,
		Center = 9,
		Right = 11
	}

	public enum PlayerStatus
	{
		Riding,
		Hit,
		Oiled,
		Boost,
		Death
	}

	#region Public Inspector

	public BikeArcadeLifes lifeLevel;
	public float tweenDuration;
	public GameObject dustParticles;
	public GameObject brokenBikePrefab;

	#endregion

	#region Private Atributes

	private PlayerStatus _playerStatus;
	private ArcadeBikesManager _arcadeManager;

	private float _startingSpeed;
	private int _lifeLevelSpacing;
	private GameObject _brokenBikeInstance; // Reference to the brokenBike Instantiated (Need it to delete after some time)

	private Rigidbody2D _rigidbody2D;
	private Animator _animator;
	private float _verticalAxis;

	#endregion

	#region Properties

	public BikeArcadeLifes LifeLevel
	{
		get { return lifeLevel; }

		set
		{
			//if ((int)value < 7)
			//{
			//	//GAME OVER
			//	Die();
			//}
			//else if ((int)value > 11)
			//{
			//	// DON'T DO ANYTHING
			//}
			//else
			//{
				//lifeLevel = value;
				//TweenPosition(new Vector2((float)value, _rigidbody2D.position.y));
			//}
		}
	}

	#endregion

	#region MonoBehaviour

	void Awake()
	{
		_arcadeManager = (ArcadeBikesManager)ArcadeBikesManager.Instance;
		_lifeLevelSpacing = (int)BikeArcadeLifes.Right - (int)BikeArcadeLifes.Center;
		_rigidbody2D = GetComponent(typeof(Rigidbody2D)) as Rigidbody2D;
		_animator = GetComponent(typeof(Animator)) as Animator;
	}

	void Start()
	{
		_playerStatus = PlayerStatus.Riding;
		_startingSpeed = _arcadeManager.speedMultiplier;

		transform.position = new Vector2((float)LifeLevel, _rigidbody2D.position.y);
	}

	void Update()
	{
		// Save vertial axis
		_verticalAxis = Input.GetAxis("Vertical");
	}

	void FixedUpdate()
	{
		if (_playerStatus == PlayerStatus.Riding)
			_rigidbody2D.velocity = Vector2.up * _verticalAxis * _arcadeManager.speedMultiplier;

		else if (_playerStatus == PlayerStatus.Oiled)
		{
			Vector2 randomDirection = Random.insideUnitCircle + (Vector2.up * _verticalAxis) * 0.6f;
			_rigidbody2D.velocity = Vector2.up * randomDirection.y * 4;
		}
	}

	void LateUpdate()
	{
		if (_playerStatus == PlayerStatus.Riding)
		{
			// animator playing speed logic
			_animator.speed = _arcadeManager.speedMultiplier / _startingSpeed * 0.7f;
			_animator.SetFloat("VerticalVelocity", _rigidbody2D.velocity.y);
		}
		else if (_playerStatus == PlayerStatus.Death)
		{
			_animator.speed = 1f;
		}

		_animator.SetBool("Riding", _playerStatus == PlayerStatus.Riding);
	}

	#endregion

	#region Utils

	public void TweenPosition(Vector2 newPosition)
	{
		if (Mathf.Abs(transform.position.x - newPosition.x) > Mathf.Epsilon)
		{
			transform.DOMove(newPosition, tweenDuration).OnComplete(() =>
			{
				Ride();
			});
		}
		else
		{
			Ride();
		}
	}

	void Ride()
	{
		_playerStatus = PlayerStatus.Riding;
	}

	IEnumerator FinishGame()
	{
		yield return new WaitForSeconds(0.5f);

		Destroy(_brokenBikeInstance);

		yield return new WaitForSeconds(0.5f);

		dustParticles.GetComponent<ParticleSystem>().Stop();
		_arcadeManager.speedMultiplier = 0;
	}

	#endregion

	#region API

	public void Hit()
	{
		if (_playerStatus == PlayerStatus.Riding)
		{
			if (lifeLevel != BikeArcadeLifes.Left)
			{
				_playerStatus = PlayerStatus.Hit;
				_animator.SetTrigger("Hit");
			}

			LifeLevel -= _lifeLevelSpacing;
		}
	}

	public void Oil()
	{
		if (_playerStatus == PlayerStatus.Riding)
		{
			_playerStatus = PlayerStatus.Oiled;
			_animator.SetTrigger("Hit");

			DOVirtual.DelayedCall(1.5f, Ride);
		}
	}

	public void Boost()
	{
		if (_playerStatus == PlayerStatus.Riding)
		{
			_playerStatus = PlayerStatus.Boost;
			_animator.SetTrigger("Jump");

			_rigidbody2D.AddForce(Vector2.up * 250f);

			Physics2D.IgnoreLayerCollision(LayerMask.NameToLayer("Player"), LayerMask.NameToLayer("Wall"), true);
		}
	}

	public void Jump()
	{
		if (_playerStatus == PlayerStatus.Riding)
		{
			_playerStatus = PlayerStatus.Boost;
			_animator.SetTrigger("Jump");

			_rigidbody2D.velocity = Vector2.zero;

			Physics2D.IgnoreLayerCollision(LayerMask.NameToLayer("Player"), LayerMask.NameToLayer("Default"), true);

			// Workaround to instantly ignore Layer Collision
			GetComponent<BoxCollider2D>().enabled = false;
			GetComponent<BoxCollider2D>().enabled = true;
		}
	}

	public void FinishJump()
	{
		_playerStatus = PlayerStatus.Riding;

		if (Physics2D.GetIgnoreLayerCollision(LayerMask.NameToLayer("Player"), LayerMask.NameToLayer("Default")))
		{
			Physics2D.IgnoreLayerCollision(LayerMask.NameToLayer("Player"),
				LayerMask.NameToLayer("Default"),
				false);
		}

		if (Physics2D.GetIgnoreLayerCollision(LayerMask.NameToLayer("Player"), LayerMask.NameToLayer("Wall")))
		{
			Physics2D.IgnoreLayerCollision(LayerMask.NameToLayer("Player"),
				LayerMask.NameToLayer("Wall"),
				false);
		}

		// Workaround to instantly ignore Layer Collision
		GetComponent<BoxCollider2D>().enabled = false;
		GetComponent<BoxCollider2D>().enabled = true;
	}

	public void Die()
	{
		if (_playerStatus == PlayerStatus.Riding || _playerStatus == PlayerStatus.Oiled || _playerStatus == PlayerStatus.Hit)
		{
			_playerStatus = PlayerStatus.Death;
			_animator.SetTrigger("Die");

			_brokenBikeInstance = Instantiate(brokenBikePrefab, transform.position, transform.rotation) as GameObject;
			_brokenBikeInstance.GetComponent<Rigidbody2D>().velocity = Vector2.right * -4 * _arcadeManager.speedMultiplier;

			StartCoroutine(FinishGame());
		}
	}

	#endregion
}
