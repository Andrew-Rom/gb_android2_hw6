package gb.jdk;

import org.apache.commons.math3.random.RandomDataGenerator;
import com.diogonunes.jcolor.*;

/**
 * Представьте, что вы стали участником игры, в которой вам нужно выбрать одну из трёх дверей.
 * За одной из дверей находится автомобиль, за двумя другими дверями — козы.
 * Вы выбираете одну из дверей, например, номер 1, после этого ведущий, который знает,
 * где находится автомобиль, а где — козы, открывает одну из оставшихся дверей,
 * например, номер 3, за которой находится коза.
 * После этого он спрашивает вас — не желаете ли вы изменить свой выбор и выбрать дверь номер 2?
 * Увеличатся ли ваши шансы выиграть автомобиль, если вы примете предложение ведущего и измените свой выбор?
 *
 * Участнику игры заранее известны следующие правила:
 *     - автомобиль равновероятно размещён за любой из трёх дверей;
 *     - ведущий знает, где находится автомобиль;
 *     - ведущий в любом случае обязан открыть дверь с козой (но не ту, которую выбрал игрок)
 *       и предложить игроку изменить выбор;
 *     - если у ведущего есть выбор, какую из двух дверей открыть
 *       (то есть, игрок указал на верную дверь, и за обеими оставшимися дверями — козы),
 *       он выбирает любую из них с одинаковой вероятностью.
 *
 * Вариант решения:
 *     - три двери;
 *     - за одной из случайным образом выбранной дверью - приз;
 *     - игрок рандомно выбирает дверь;
 *     - ведущий открывает дверь, за которой нет приза и которую, при этом, не выбрал игрок;
 *     - предполагаем, что с этого момента существуют 2 параллельные игры в одной игрок не меняет
 *       свой первоначальный выбор (игра-1), во второй - меняет(игра-2);
 *     - двери открываются;
 *     - учитывая количество оставшихся дверей, если игрок в игре-1 выиграл - значит,
 *       в игре-2 он проиграл и наоборот.
 *     Выводим процент успеха в обеих играх.
 */

public class Main {
    public static void main(String[] args) {

        int DOORS = 3;
        int GAMES = 5000;

        double victoriesKeepSelection = 0;
        double victoriesChangeSelection = 0;

        int currentGame = 0;

        while (currentGame < GAMES) {
            int wonDoor = new RandomDataGenerator().nextInt(0, DOORS-1);
            int playerDoor = new RandomDataGenerator().nextInt(0, DOORS-1);

            if (playerDoor == wonDoor) {
                victoriesKeepSelection++;
            } else {
                victoriesChangeSelection++;
            }
            currentGame++;

        }

        System.out.println(Ansi.colorize(("The probability of winning with keeping selection: "
                + (victoriesKeepSelection * 100) / GAMES + "%."), Attribute.BRIGHT_RED_TEXT()));
        System.out.println(Ansi.colorize(("The probability of winning with changing selection: "
                + (victoriesChangeSelection * 100) / GAMES  + "%."), Attribute.GREEN_TEXT()));

    }
}