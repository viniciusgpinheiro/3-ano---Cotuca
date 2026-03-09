using UnityEngine;
using System;

/// <summary>
/// Prefab attribute. Use this on child classes
/// to define if they have a prefab associated or not
/// By default will attempt to load a prefab
/// that has the same name as the class,
/// otherwise [Prefab("path/to/prefab", bool persistent)]
/// to define it specifically. 
/// EJ:
/// [Prefab("name", true)] carga el prefab name y lo hace persistente entre escenas
/// [Prefab(""), true)] crea un GameObject de tipo T y lo hace persistente.
/// </summary>
[AttributeUsage(AttributeTargets.Class, Inherited = true)]
public class PrefabAttribute : Attribute
{
    private string _name;
    public string Name { get { return _name; } }

    private bool _persistent;
    public bool Persintent { get { return _persistent; } }

    private bool _childOfSingleton;
    public bool ChildOfSingleton { get { return _childOfSingleton; } }

    public PrefabAttribute()
    {
        _name = "";
        _persistent = false;
        _childOfSingleton = false;
    }
    public PrefabAttribute(string name, bool persistent = false, bool childofSingleton = false)
    {
        _name = name;
        _persistent = persistent;
        _childOfSingleton = childofSingleton;
    }
}

/// <summary>
/// MONOBEHAVIOR PSEUDO SINGLETON ABSTRACT CLASS
/// 
/// usage: can be attached to a gameobject and if not this will create one on first access
/// 
/// example: "public sealed class MyClass : Singleton<MyClass>"
/// </summary>
public abstract class Singleton<T> : MonoBehaviour where T : MonoBehaviour
{

    private static T _instance = null;
    public static bool IsAwake { get { return (_instance != null); } }

    private static GameObject _gameObject;

    /// <summary>
    /// gets the instance of this Singleton
    /// use this for all instance calls:
    /// MyClass.Instance.MyMethod();
    /// or make your public methods static
    /// and have them use Instance internally
    /// for a nice clean interface
    /// </summary>
    public static T Instance
    {
        get
        {
            Type mytype = typeof(T);

            if (_instance == null)
            {
                _instance = (T)FindObjectOfType(mytype);

                if (_instance == null)
                {
                    string goName = mytype.ToString();
                    GameObject go = GameObject.Find(goName);

                    if (go == null) // try again searching for a cloned object
                        go = GameObject.Find(goName);

                    if (go == null) //if still not found try prefab or create
                    {
                        PrefabAttribute attr = (PrefabAttribute)Attribute.GetCustomAttribute(mytype, typeof(PrefabAttribute));
                        // checks if the [Prefab] attribute is set and pulls that if it can
                        if (attr != null)
                        {
                            try
                            {
                                if (!string.IsNullOrEmpty(attr.Name))
                                {
                                    go = (GameObject)Instantiate(Resources.Load(attr.Name, typeof(GameObject)));
                                    go.name = go.name.Remove(go.name.Length - 7, 7);
                                }
                                else
                                    go = new GameObject(goName);
                            }
                            catch (Exception e)
                            {
                                Debug.LogError("could not instantiate prefab even though prefab attribute was set: " + e.Message + "\n" + e.StackTrace);
                            }
                            if (attr.Persintent)
                                DontDestroyOnLoad(go);
                            if (attr.ChildOfSingleton)
                                SetParent(go, "Singletons");
                        }
                        if (go == null)
                        {
                            //Debug.LogWarning(goName + " not found creating...");
                            go = new GameObject();
                            go.name = goName;
                        }
                        _gameObject = go;
                    }
                    _instance = go.GetComponent<T>();
                    if (_instance == null)
                        _instance = go.AddComponent<T>();

                }
                else
                {
                    //Debug.Log(mytype.Name + " had to be searched for but was found"); 
                    int count = FindObjectsOfType(mytype).Length;
                    if (count > 1)
                    {
                        Debug.LogError("Singleton: there are " + count.ToString() + " of " + mytype.Name);
                        throw new Exception("Too many (" + count.ToString() + ") prefab singletons of type: " + mytype.Name);
                    }
                }
            }

            return _instance;
        }
    }

    /// <summary>
    /// for garbage collection
    /// </summary>
    public virtual void OnApplicationQuit()
    {
        // release reference on exit
        _instance = null;
    }

    public virtual void Reset()
    {
        // release reference on exit
        Destroy(_gameObject);
        _instance = null;
    }

    static void SetParent(GameObject go, string parentGOName)
    {
        if (!string.IsNullOrEmpty(parentGOName))
        {
            GameObject parentGO = GameObject.Find(parentGOName);
            if (parentGO == null)
            {
                parentGO = new GameObject(parentGOName);
                parentGO.transform.parent = null;
                DontDestroyOnLoad(parentGO);
            }
            go.transform.SetParent(parentGO.transform);
        }
    }

}