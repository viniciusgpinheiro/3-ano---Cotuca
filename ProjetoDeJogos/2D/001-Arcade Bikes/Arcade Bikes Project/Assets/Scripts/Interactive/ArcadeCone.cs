using UnityEngine;
using System.Collections;


public class ArcadeCone : ArcadeObstacle
{
	protected override void OnTriggerEnter2D(Collider2D col)
	{
		if (col.CompareTag("ArcadePlayer") && Status == ArcadeObstacleStatus.Default) {
			Status = ArcadeObstacleStatus.Disabled;

			if (smoke)
				smoke.SetTrigger("Hit");

			if (_animator)
				_animator.SetTrigger("Hit");

			GetComponent<Rigidbody2D>().velocity = Vector2.zero;
			GetComponent<Rigidbody2D>().AddForce(Vector2.right * ArcadeBikesManager.Instance.speedMultiplier * 50);

			StartCoroutine(StopAndBackAgain());
		}
	}

	IEnumerator StopAndBackAgain()
	{
		yield return new WaitForSeconds(0.5f);

		GetComponent<Rigidbody2D>().velocity = Vector2.right * ArcadeBikesManager.Instance.speedMultiplier * -4;
	}
}

