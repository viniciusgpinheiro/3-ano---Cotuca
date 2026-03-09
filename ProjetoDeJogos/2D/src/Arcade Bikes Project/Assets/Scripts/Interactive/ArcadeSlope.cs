using UnityEngine;

public class ArcadeSlope : ArcadeObstacle
{
	protected override void OnTriggerEnter2D(Collider2D col)
	{
		if (col.CompareTag("ArcadePlayer") && Status == ArcadeObstacleStatus.Default && col.CompareTag("ArcadePlayer"))
		{
			col.SendMessage("Jump");
		}
	}
}
