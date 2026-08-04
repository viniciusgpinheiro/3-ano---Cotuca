using System.Collections;
using UnityEngine;

public class PatrulhaDoOponente : MonoBehaviour
{
    
    public float velocidade = 1f;
    public float minX, maxX;
    public float tempoDeEspera = 2f;
    private GameObject _meta;
    private Animator _animator;
    private Arma _arma;

    private void Awake()
    {
        _animator = GetComponent<Animator>();
        _arma = GetComponentInChildren<Arma>();
    }

    private void AtualizarMeta()
    {
        if (_meta == null)
        {
            _meta = new GameObject("Alvo");
            _meta.transform.position = new Vector2(minX, transform.position.y);
            return;
        }
        if (_meta.transform.position.x <= minX)
        {
            _meta.transform.position = new Vector2(maxX, transform.position.y);
            transform.localScale = new Vector3(1, 1, 1);
        }
        else if (_meta.transform.position.x >= maxX)
        {
            _meta.transform.position = new Vector2(minX, transform.position.y);
            transform.localScale = new Vector3(-1, 1, 1);
        }
    }

    IEnumerator PatrulhaAteMeta()
    {
        while (Vector2.Distance(transform.position, _meta.transform.position) > 0.05f)
        {
            _animator.SetBool("idle", false);
            Vector2 direcao = _meta.transform.position - transform.position;
            float direcaX = direcao.x;
            transform.Translate(direcao.normalized * velocidade * Time.deltaTime);
            yield return null;
        }

        Debug.Log("Meta encontrada!");
        transform.position = new Vector2(_meta.transform.position.x, transform.position.y);
        AtualizarMeta();        
        _animator.SetBool("idle", true);
        // if (_arma != null)
        // {
        //     _arma.Atirar();
        // }
        _animator.SetTrigger("shooting");
        Debug.Log($"Esperando por {tempoDeEspera}s");
        yield return new WaitForSeconds(tempoDeEspera);
        Debug.Log("Atualiaando a meta!");
        StartCoroutine("PatrulhaAteMeta");
    }

    void PodeAtirar()
    {
        if (_arma != null)
        {
            _arma.Atirar();
        }
    }

    void Start()
    {
        AtualizarMeta();
        StartCoroutine("PatrulhaAteMeta");
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
