using System;
using System.Collections.Generic;

namespace Trie
{
    class Program
    {
        public readonly int alphabet = 62;
        public class TrieNode
        {
            public TrieNode[] children;
            public TrieNode parent;
            public char value;
            public bool endOfWord;

            public TrieNode(TrieNode parent, char value)
            {
                this.children = new TrieNode[62];
                this.parent = parent;
                this.value = value;
            }

            public TrieNode()
            {
                this.children = new TrieNode[62];
            }
        }

        public class Trie
        {
            TrieNode root;

            public Trie()
            {
                root = new TrieNode();
            }
            public void Insert(String key)
            {
                int level;
                int lenght = key.Length;

                TrieNode current = root;
                char c;

                for (level = 0; level < lenght; level++)
                {
                    c = key[level];

                    for (int i = 0; i < current.children.Length; i++)
                    {
                        if (current.children[i] != null)
                        {
                            if (current.children[i].value == c)
                            {
                                current = current.children[i];
                                break;
                            }
                        }
                        else
                        {
                            for (int j = 0; j < 62; j++)
                            {
                                if (current.children[j] == null)
                                {
                                    current.children[j] = new TrieNode(current, c);
                                    current = current.children[j];
                                    i = current.children.Length;
                                    break;
                                }
                            }
                        }
                    }
                }

                current.endOfWord = true;
            }

            public TrieNode Lookup(String key)
            {
                int level;
                int lenght = key.Length;
                TrieNode current = root;
                char c;

                for (level = 0; level < lenght; level++)
                {
                    c = key[level];
                    for (int i = 0; i < current.children.Length; i++)
                    {
                        if (current.children[i] != null)
                        {
                            if (current.children[i].value == c)
                            {
                                current = current.children[i];
                                break;
                            }
                        }
                    }
                }

                if (current.endOfWord)
                {
                    return current;
                }
                else
                {
                    return null;
                }
            }

            public void Delete(String key)
            {
                TrieNode current = Lookup(key);

                if (current == null)
                {
                    return;
                }

                char c = current.value;
                int level;
                int length = key.Length;

                for (level = 0; level < length; level++)
                {
                    for (int i = 0; i < current.children.Length; i++)
                    {
                        if (current.children[i] != null)
                        {
                            current.endOfWord = false;
                            return;
                        }
                    }
                    current = current.parent;

                    for (int i = 0; i < current.children.Length; i++)
                    {
                        if (current.children[i] != null)
                        {
                            if (current.children[i].value == c)
                            {
                                current.children[i] = null;
                            }
                        }
                    }
                }

                current.endOfWord = true;
            }
            private void print(TrieNode root, int depth)
            {
                Console.Write(root.value);

                if (root.endOfWord)
                {
                    Console.WriteLine();

                   /* for (int i = 0; i < depth; i++)
                    {
                        Console.Write(" ");
                    }*/
                }

                for (int i = 0; i < root.children.Length; i++)
                {
                    if (root.children[i] == null)
                    {
                        continue;
                    }
                    else
                    {
                        Console.Write(depth);
                        print(root.children[i], depth + 1);
                    }
                }
            }

            public void Print()
            {
                print(this.root, 0);
            }
        }
        static void Main(string[] args)
        {
            Trie tree = new Trie();

            String[] keys = { "Hello", "Hell", "Hail", "Hentai", "Henry"};
            
            for (int i = 0; i < keys.Length; i++)
            {
                tree.Insert(keys[i]);
            }

            tree.Print();
        }
    }
}
