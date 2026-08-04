using System.Collections;
using System.Collections.Generic;
using Mono.Cecil.Cil;
using UnityEngine;

public class ChangeColor : MonoBehaviour
{   
    SpriteRenderer srObjeto;
    void Awake() 
    {
        Debug.Log("Estou associado a um objeto da tela atual!"); 
    }

    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("O jogador acabou de pressionar o botão PLAY.");
        srObjeto = this.GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
        // Verificaremos digitação de teclas pelo jogador e mudaremos cores
        if (Input.GetKeyDown(KeyCode.R))
            srObjeto.color = Color.red;
        else if (Input.GetKeyDown(KeyCode.G))
            srObjeto.color = Color.green;
        else if (Input.GetKeyDown(KeyCode.Y))
            srObjeto.color = Color.yellow;
        else if (Input.GetKeyDown(KeyCode.C))
            srObjeto.color = Color.cyan;  
    }
}
