import QL.CoreTools.converters as converters
import QL.config as c

class Processor:

    @staticmethod
    def eval_expression(expression, answers_map):
        answers_dict = answers_map.get_answers()
        # to avoid annoying (not applicable) error messages
        answers_dict['__builtins__'] = None
        try:
            result = eval(expression, answers_dict)
            if "test" in answers_dict and expression == "test == \"A\"":
                print(expression)
                print(answers_dict['test'] == "\"A\"")
                print(result)
                print(answers_dict)
            return result
        except Exception as e:
            if expression == "test == \"A\"":
                print(e)
            return False

    @staticmethod
    def export_answers(answers_map, gui):
        xml = converters.Converters.dict_to_xml(answers_map.get_answers())
        f = open(c.Config.output_path, 'w')
        f.write(xml)
        f.close()
        print("done")
        gui.close()