using UnityEngine;
using System.Collections;


public class ArcadeOil : ArcadeObstacle
{
	protected override void OnTriggerEnter2D(Collider2D col)
	{
		if (col.CompareTag("ArcadePlayer") && Status == ArcadeObstacleStatus.Default) {
			Status = ArcadeObstacleStatus.Disabled;

			if (_animator)
				_animator.SetTrigger("Hit");

			col.SendMessage("Oil");
		}
	}
}
