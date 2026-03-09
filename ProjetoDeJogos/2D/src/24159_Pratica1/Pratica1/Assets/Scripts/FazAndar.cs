using System;
using System.Xml.Serialization;
using Unity.Collections;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.Rendering;

public class FazAndar : MonoBehaviour
{

    private SpriteRenderer personagemSpriteRenderer;

    void Awake()
    {
        personagemSpriteRenderer = GetComponent<SpriteRenderer>();
    }

    void Update()
    {
        // No novo sistema, lemos as teclas assim:
        Vector2 inputMovimento = Vector2.zero;
        float moveX = inputMovimento.x;

        if (Keyboard.current.wKey.isPressed) {inputMovimento.y = 1;}
        if (Keyboard.current.sKey.isPressed) {inputMovimento.y = -1;}
        if (Keyboard.current.aKey.isPressed) {inputMovimento.x = -1;}
        if (Keyboard.current.dKey.isPressed) {inputMovimento.x = 1;}

        if (moveX > inputMovimento.x)
        {
            personagemSpriteRenderer.flipX = true;    
        }
        else if (moveX < inputMovimento.x)
        {
            personagemSpriteRenderer.flipX = false;
        }
        

        Vector2 movimento = inputMovimento.normalized * Time.deltaTime;
        transform.Translate(movimento);
    } 
}