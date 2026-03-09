using UnityEngine;
using System.Collections;


public class ArcadeBarrelYellow : ArcadeObstacle
{
	protected override void OnTriggerEnter2D(Collider2D col)
	{
		if (col.CompareTag("ArcadePlayer")) {
			base.OnTriggerEnter2D(col);
		}
	}
}

