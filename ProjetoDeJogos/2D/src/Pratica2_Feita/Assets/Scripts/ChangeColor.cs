using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChangeColor : MonoBehaviour
{
	public Color[] cores;

	private int qualCor = 0;
	private SpriteRenderer srObjeto;

	// Awake is good to get references from the same object or others in the scene
	private void Awake()
	{
		Debug.Log("I'm attached as a component of an object in the Scene!");

		// referência ao SpriteRenderer do objeto associado a este script
		srObjeto = this.GetComponent<SpriteRenderer>();
		qualCor = 0;  
	}

	// Start is called before the first frame update
	void Start()
	{
		Debug.Log("You have just pressed PLAY BUTTON!");
	}

	// Update is called once per frame
	void Update()
	{
		/* Let's check some
		 * input from keyboard
		 * and change colors! */
		if (srObjeto != null)		// se conseguiu referenciar um SpriteRenderer no Awake()
		{
			if (Input.GetKeyDown(KeyCode.R)) 
				srObjeto.color = Color.red;
			else
				if (Input.GetKeyDown(KeyCode.Y)) 
					srObjeto.color = Color.yellow;
				else
					if (Input.GetKeyDown(KeyCode.C)) 
						srObjeto.color = Color.cyan;
					else
						if (Input.GetKeyDown(KeyCode.G)) 
							srObjeto.color = Color.green;
			
			if (Input.GetKeyUp(KeyCode.Space))
			{
				srObjeto.color = cores[qualCor];
				if (++qualCor == cores.Length)  // já percorreu todas as cores do vetor
				   qualCor = 0;					// retorna à primeira posição do vetor
			}

		}
//		else
//			Debug.Log("Objeto sem SpriteRenderer!");		
	}
}
