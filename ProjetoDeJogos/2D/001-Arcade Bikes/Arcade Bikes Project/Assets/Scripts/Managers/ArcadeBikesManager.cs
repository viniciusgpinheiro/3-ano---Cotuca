using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;


public class ArcadeBikesManager : Singleton<ArcadeBikesManager>
{
	// Editor
	[Range(0f, 10f)] public float speedMultiplier = 1f;   // Multiplicador para sincronizar todos los "ParallaxLoops" desde el ArcadeManager.
}
