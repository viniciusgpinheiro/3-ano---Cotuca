using UnityEngine;
using System.Collections.Generic;

public enum ObstacleType
{
	None = -1,
	BarrelYellow,
	BarrierSignBig,
	BarrierSignSmall,
	ConcreteBarrier,
	Cone,
	OilStainBig,
	OilStainSmall,
	RockPileBig,
	RockPileSmall,
	Slope,
	SlopeGround
}

public class ObstacleGeneratorManager : MonoBehaviour
{
	#region Public Inspector

	public ObstacleGenerator _LinesObstacleGenerator;
	public List<GameObject> obstaclePrefabs;

	#endregion

	#region Private Atributes

	private float _gameTimer;
	private List<GameObject> _prefabPooling;
	private ArcadeBikesManager _arcadeManager;
	#endregion

	#region Properties



	#endregion

	#region Events & Subscribers



	#endregion

	#region MonoBehaviour

	void Awake()
	{
		_arcadeManager = (ArcadeBikesManager)ArcadeBikesManager.Instance;
		_prefabPooling = GenerateGameObjects(this.transform, GetObstacleTypeInGenerator(_LinesObstacleGenerator), 5);
	}

	void Start()
	{

	}

	void Update()
	{
		_gameTimer += Time.deltaTime;

		if (_LinesObstacleGenerator.nextObstacle.atTime != -1 & _LinesObstacleGenerator.nextObstacle.atTime <= _gameTimer) {
			InstantiateFromPooling(_LinesObstacleGenerator.nextObstacle);
			_LinesObstacleGenerator.SelectNextObstacle();
		}
	}

	void OnTriggerEnter2D(Collider2D col)
	{
		if (col.CompareTag("ArcadeObstacle")) {
			Animator anim = col.GetComponent(typeof(Animator)) as Animator;
			if (anim)
				anim.SetTrigger("Restart");

			col.GetComponent<Rigidbody2D>().velocity = Vector2.zero;
			col.transform.position = Vector2.one * -30f;
			col.GetComponent<ArcadeObstacle>().DisableObstacle();

			col.gameObject.SetActive(false);
		}
	}

	#endregion

	#region Utils

	List<ObstacleType> GetObstacleTypeInGenerator(ObstacleGenerator generator)
	{
		List<ObstacleType> res = new List<ObstacleType>();
		foreach (ObstacleInfo i in generator.obstacleList) {
			foreach (ObstacleType t in i.obstacletypes) {
				if (t != ObstacleType.None && !res.Contains(t))
					res.Add(t);
			}
		}
		return res;
	}

	List<GameObject> GenerateGameObjects(Transform parent, List<ObstacleType> types, int amount)
	{
		List<GameObject> res = new List<GameObject>();
		foreach (ObstacleType t in types) {
			if (t != ObstacleType.None) {
				GameObject gameObjectType = obstaclePrefabs.Find(x => x.name == t.ToString());
				for (int i = 0; i < amount; i++) {
					GameObject prefab = Instantiate(gameObjectType, Vector2.one * -30f, Quaternion.identity) as GameObject;
					prefab.name = t.ToString();
					prefab.transform.parent = parent;
					prefab.SetActive(false);
					res.Add(prefab);
				}
			}
		}
		return res;
	}
	GameObject GetFromPooling(ObstacleType type)
	{
		GameObject res = _prefabPooling.Find(x => x.name == type.ToString() && !x.activeSelf);
		res.SetActive(true);
		return res;
	}
	void InstantiateFromPooling(ObstacleInfo nextObstacle)
	{
		for (int i = 0; i < nextObstacle.obstacletypes.Length; i++) {
			if (nextObstacle.obstacletypes[i] != ObstacleType.None) {
				if (nextObstacle.isSurprise) {
					int random = Random.Range(0, nextObstacle.obstacletypes.Length);
					GameObject prefab = GetFromPooling(nextObstacle.obstacletypes[random]);

					float centerOfMassY = prefab.transform.Find("CenterOfMass").position.y;
					prefab.transform.position = new Vector3(
						_LinesObstacleGenerator.linesPositions[random].position.x,
						_LinesObstacleGenerator.linesPositions[random].position.y + (prefab.transform.position.y - centerOfMassY),
						_LinesObstacleGenerator.linesPositions[random].position.z
					);
				} else {
					GameObject prefab = GetFromPooling(nextObstacle.obstacletypes[i]);
					float centerOfMassY = prefab.transform.Find("CenterOfMass").position.y;
					prefab.transform.position = new Vector3(
						_LinesObstacleGenerator.linesPositions[i].position.x,
						_LinesObstacleGenerator.linesPositions[i].position.y + (prefab.transform.position.y - centerOfMassY),
						_LinesObstacleGenerator.linesPositions[i].position.z
					);
					prefab.GetComponent<Rigidbody2D>().velocity = Vector2.right * -4 * _arcadeManager.speedMultiplier;
					prefab.GetComponent<ArcadeObstacle>().EnableObstacle();

				}
			}
		}
	}
	#endregion
}
