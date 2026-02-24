using UnityEngine;
using DG.Tweening;
using System.Collections.Generic;


public class ArcadeObstacle : MonoBehaviour
{
	public enum ArcadeObstacleDamage
	{
		None,
		Hit,
		Lethal
	}

	public enum ArcadeObstacleStatus
	{
		Disabled,
		Selected,
		Default
	}

	#region Public Inspector

	[SerializeField]
	protected ArcadeObstacleDamage damageType;
	[SerializeField]
	protected Animator smoke;

	#endregion

	#region Private Atributes

	private List<Collider2D> colliders = new List<Collider2D>();
	protected Animator _animator;

	#endregion

	#region Properties

	public ArcadeObstacleStatus Status;

	#endregion

	#region MonoBehaviour

	protected virtual void Awake()
	{
		Status = ArcadeObstacleStatus.Disabled;
		_animator = GetComponent(typeof(Animator)) as Animator;
	}

	protected virtual void OnEnable()
	{

	}

	protected virtual void OnTriggerEnter2D(Collider2D col)
	{
		if (col.CompareTag("ArcadePlayer") && Status == ArcadeObstacleStatus.Default) {
			Status = ArcadeObstacleStatus.Disabled;

			if (smoke)
				smoke.SetTrigger("Hit");

			if (_animator)
				_animator.SetTrigger("Hit");

			//Camera.main.DOShakePosition(0.5f, 0.5f, 30, 90f).OnComplete(() => Camera.main.transform.position = new Vector3(0f, 0f, -20f));

			if (damageType == ArcadeObstacleDamage.Hit) {
				if (col.CompareTag("ArcadePlayer")) {
					col.SendMessage("Hit");
				}
			} else if (damageType == ArcadeObstacleDamage.Lethal) {

				if (col.CompareTag("ArcadePlayer")) {
					col.SendMessage("Die");
				}
			}

		}
	}

	#endregion

	#region API

	public void EnableObstacle()
	{
		Status = ArcadeObstacleStatus.Default;
	}

	public void DisableObstacle()
	{
		Status = ArcadeObstacleStatus.Disabled;
	}

	#endregion
}
