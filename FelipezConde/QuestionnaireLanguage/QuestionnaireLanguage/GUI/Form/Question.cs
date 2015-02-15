﻿using QuestionnaireLanguage.GUI.Elements;
using QuestionnaireLanguage.GUI.Factories.ElementFactory;
using QuestionnaireLanguage.GUI.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace QuestionnaireLanguage.GUI.Form
{
    class Question : FormBase, IForm
    {
        public Question(string type)
        {
            //System.Type elementType = System.Type.GetType(type);

            //object objType = Activator.CreateInstance(elementType);

            CreateForm(type);
        }

        public Control CreateForm(object typeObject)
        {
            Control control = new Control();

            TextBoxElement element = ElementFactory.ObtainTextBox(Id, Label);

            return element.GetControl();
        }
        public Control CreateForm1(string type)
        {
            //TextBoxElement element = ElementFactory.ObtainTextBox(Id, Label);
            IElement element = ElementFactory.GetElement(type);

            element.Id = Id;
            element.Label = Label;

            Control c = element.GetControl();


            return element.GetControl();
        }
    }
}
