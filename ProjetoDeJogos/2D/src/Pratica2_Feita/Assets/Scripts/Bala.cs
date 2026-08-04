using UnityEngine;

public class Bala : MonoBehaviour
{
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    public float vel = 2.0f;
    public Vector2 dir;
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        Vector2 move = dir.normalized * vel * Time.deltaTime;
        this.transform.Translate(move);
    }
}
