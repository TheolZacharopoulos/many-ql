﻿using AST.Nodes.Expressions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TypeChecking
{
    public class DependencyGraph
    {
        private Dictionary<Id, IEnumerable<Id>> dependencies = new Dictionary<Id, IEnumerable<Id>>();

        public void AddNewDirectDepencencies(Id source, IEnumerable<Id> dependencies)
        {
            this.dependencies[source] =  dependencies;
        }

        public void Combine(DependencyGraph dependencyGraph)
        {
            foreach (KeyValuePair<Id, IEnumerable<Id>> dependency in dependencyGraph.dependencies)
            {
                this.dependencies[dependency.Key] = dependency.Value;
            }
        }

        public IEnumerable<Id> GetCycles()
        {
            FindCycle();
            
            foreach (Id key in dependencies.Keys.Where(key => dependencies[key].Contains(key)))
            {
                yield return key;
            }
        }

        private void FindCycle()
        {
            for (int i = 0; i < dependencies.Count; i++)
            {
                Expand();
            }
        }

        private void Expand()
        {
            foreach (KeyValuePair<Id, IEnumerable<Id>> dependency in dependencies)
            {
                foreach (IEnumerable<Id> childDependencies in dependencies[dependency.Key])
                {
                    dependencies[dependency.Key].Concat(childDependencies);
                }
            }
        }
    }
}
