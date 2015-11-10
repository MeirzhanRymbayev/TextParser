package com.epam.mrymbayev;
/*Task2
Создать программу обработки текста учебника по программированию с использованием классов:
Символ, Слово, Предложение, Знак препинания и др.
Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.

5.	В каждом предложении текста поменять местами первое слово с последним, не изменяя длины предложения.
*/
import com.epam.mrymbayev.entity.Component;
import com.epam.mrymbayev.entity.Sentence;
import com.epam.mrymbayev.entity.Text;
import com.epam.mrymbayev.io.MTextReader;
import com.epam.mrymbayev.io.exception.ReadingException;
import com.epam.mrymbayev.parser.SimpleParser;
import com.epam.mrymbayev.parser.exception.ParseException;
import com.epam.mrymbayev.parser.exception.PropertyFilePathException;
import org.apache.log4j.Logger;

/**
 * Hello world!
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws ReadingException, PropertyFilePathException, ParseException {

        MTextReader reader = new MTextReader();
        String s = reader.getFullText("src/main/resources/File1.txt");
        SimpleParser simpleParser = new SimpleParser();
        Text parsedText = simpleParser.parse(s);

        for (Component component: parsedText.getComponents()) {
            System.out.println(component.toSourceString());
        }

        Sentence sentence = new Sentence();


    }
}
