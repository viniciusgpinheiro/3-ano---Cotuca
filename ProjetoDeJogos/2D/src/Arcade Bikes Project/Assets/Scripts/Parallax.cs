using UnityEngine;
using System.Collections.Generic;

public class Parallax : MonoBehaviour
{
    public Vector2 speed;
    public float createAt;
    public float deleteAt;

    public bool pause { get; set; }

    private List<Transform> _childs;
	private ArcadeBikesManager _arcadeManager;

    void Awake()
    {
        _arcadeManager = (ArcadeBikesManager)ArcadeBikesManager.Instance;
        _childs = new List<Transform>();

        foreach (Transform t in transform) {
            _childs.Add(t);
        }
    }

    void Start ()
    {

    }

    void Update()
    {
        if (!pause)
        {
            for (int i = 0; i < _childs.Count; i++)
            {
                if (_childs[i].position.x <= deleteAt)
                    _childs[i].position = new Vector3(_childs[i].position.x + createAt * 2, _childs[i].position.y, _childs[i].position.z);

                _childs[i].Translate(speed * Time.deltaTime * _arcadeManager.speedMultiplier);
            }
        }
    }
}
