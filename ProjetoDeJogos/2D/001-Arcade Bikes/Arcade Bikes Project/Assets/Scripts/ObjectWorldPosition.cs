using UnityEngine;
using System.Collections.Generic;



[ExecuteInEditMode]
public class ObjectWorldPosition : MonoBehaviour
{
	protected Transform _shadowTransform;
	public virtual Transform ShadowTransform {
		get {
			if (_shadowTransform == null)
				_shadowTransform = transform.Find("Shadow");

			return _shadowTransform;
		}
	}

	protected Transform _spriteTransform;
	public virtual Transform SpriteTransform {
		get {
			if (_spriteTransform == null)
				_spriteTransform = transform.Find("Sprite");

			return _spriteTransform;
		}
	}

	public Vector2 CurrentPosition {
		get {
			return (Vector2)ShadowTransform.position;
		}
	}

	protected SpriteRenderer _spriteRenderer;
	public virtual SpriteRenderer SpriteRenderer {
		get {
			if (_spriteRenderer == null)
				_spriteRenderer = SpriteTransform.GetComponent(typeof(SpriteRenderer)) as SpriteRenderer;

			return _spriteRenderer;
		}
	}

	protected virtual void OnEnable()
	{
		transform.position = Depth();
	}

	protected virtual void Awake()
	{
		if (ShadowTransform == null)
			Debug.LogErrorFormat("ObjectWorldPosition Error: No se encontro un GameObject \"Shadow\" en {0}", gameObject.name);
	}

	protected virtual void Start()
	{
		transform.position = Depth();
	}

	protected virtual Vector3 Depth()
	{
		float z = (ShadowTransform.position.y - .64f) / 10000f;
		return new Vector3(transform.position.x, transform.position.y, z);
	}
}
