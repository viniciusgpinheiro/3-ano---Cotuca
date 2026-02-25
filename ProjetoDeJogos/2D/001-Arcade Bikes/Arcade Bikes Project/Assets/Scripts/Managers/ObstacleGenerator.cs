using UnityEngine;
using System.Collections.Generic;

[System.Serializable]
public struct ObstacleInfo
{
	public float atTime;
	public bool isSurprise;
	public bool ignoreAlert;
	public ObstacleType[] obstacletypes;

	public ObstacleInfo(ObstacleType[] t, float at, bool s, bool i)
	{
		atTime = at;
		obstacletypes = t;
		isSurprise = s;
		ignoreAlert = i;
	}
}

public class ObstacleGenerator : MonoBehaviour
{
	#region Public Inspector
	public Transform[] linesPositions;
	public List<ObstacleInfo> obstacleList;

	#endregion

	#region Private Atributes


	#endregion

	#region Properties

	public ObstacleInfo nextObstacle { get; private set; }

	#endregion

	#region Events & Subscribers



	#endregion

	#region MonoBehaviour

	void Awake()
	{
		nextObstacle = new ObstacleInfo(new ObstacleType[] { ObstacleType.None, ObstacleType.None, ObstacleType.None }, -1, false, false);
	}

	void Start()
	{
		SelectNextObstacle();
	}

	#endregion

	#region Utils

	public void SelectNextObstacle()
	{

		if (obstacleList.Count > 0) {
			nextObstacle = obstacleList[0];
			obstacleList.RemoveAt(0);
		} else {
			nextObstacle = new ObstacleInfo(new ObstacleType[] { ObstacleType.None, ObstacleType.None, ObstacleType.None }, -1, false, false);
		}
	}

	#endregion

	#region API

	public void InstantiateObstacle(GameObject[] obstacles)
	{
		//      Debug.Log (obstacles[0]+" - "+obstacles[1]+" - "+obstacles[2]);

		if (obstacles != null && obstacles.Length == 3) {
			if (nextObstacle.isSurprise) {
				int random = Random.Range(0, 3);
				float centerOfMassY = obstacles[random].transform.Find("CenterOfMass").position.y;
				obstacles[random].transform.position = new Vector3(
					linesPositions[random].position.x,
					linesPositions[random].position.y + (obstacles[random].transform.position.y - centerOfMassY),
					linesPositions[random].position.z
				);
				obstacles[random].GetComponent<Rigidbody2D>().velocity = Vector2.right * -4 * ArcadeBikesManager.Instance.speedMultiplier;
				obstacles[random].GetComponent<ArcadeObstacle>().EnableObstacle();
			} else {
				for (int i = 0; i < nextObstacle.obstacletypes.Length; i++) {
					if (obstacles[i] != null) {
						float centerOfMassY = obstacles[i].transform.Find("CenterOfMass").position.y;
						obstacles[i].transform.position = new Vector3(
							linesPositions[i].position.x,
							linesPositions[i].position.y + (obstacles[i].transform.position.y - centerOfMassY),
							linesPositions[i].position.z
						);
						obstacles[i].GetComponent<Rigidbody2D>().velocity = Vector2.right * -4 * ArcadeBikesManager.Instance.speedMultiplier;
						obstacles[i].GetComponent<ArcadeObstacle>().EnableObstacle();
					}
				}
			}
		}
		SelectNextObstacle();
	}
	#endregion
}