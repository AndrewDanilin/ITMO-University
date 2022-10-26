Домашнее задание 2. Сумма чисел
----
1.  Разработайте класс `Sum`, который при запуске из командной строки будет складывать переданные в качестве аргументов целые числа и выводить их сумму на консоль.
2.  Примеры запуска программы:
    
    `java Sum 1 2 3`
    
    Результат: 6
    
    `java Sum 1 2 -3`
    
    Результат: 0
    
    `java Sum "1 2 3"`
    
    Результат: 6
    
    `java Sum "1 2" " 3"`
    
    Результат: 6
    
    `java Sum " "`
    
    Результат: 0
    
    Аргументы могут содержать:
    *   цифры;
    *   знаки `+` и `-`;
    *   произвольные [пробельные символы](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#isWhitespace(char)).
3.  При выполнении задания можно считать, что для представления входных данных и промежуточных результатов достаточен тип `int`.
4.  Перед выполнением задания ознакомьтесь с документацией к классам [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) и [Integer](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html).
5.  Для отладочного вывода используйте [System.err](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#err), тогда он будет игнорироваться проверяющей программой.

### Реализация: [Sum](https://github.com/AndrewDanilin/ITMO-University/tree/main/prog-intro/java-solutions/sum)

Домашнее задание 5. Свой сканер
----

1.  Реализуйте свой аналог класса [Scanner](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html) на основе [Reader](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/Reader.html).
2.  Примените разработанный `Scanner` для решения задания «Реверс».
3.  Примените разработанный `Scanner` для решения задания «Статистика слов».
4.  Нужно использовать блочное чтение. Код, управляющий чтением, должен быть общим.
5.  _Сложный вариант_. Код, выделяющий числа и слова, должен быть общим.
6.  Обратите внимание на:
    *   Обработку ошибок.
    *   На слова/числа, пересекающие границы блоков, особенно — больше одного раза.

### Реализация: [Scanner](https://github.com/AndrewDanilin/ITMO-University/tree/main/prog-intro/java-solutions/scanner)

Домашнее задание 7. Разметка
----

1.  Разработайте набор классов для текстовой разметки.
2.  Класс `Paragraph` может содержать произвольное число других элементов разметки и текстовых элементов.
3.  Класс `Text` – текстовый элемент.
4.  Классы разметки `Emphasis`, `Strong`, `Strikeout` – выделение, сильное выделение и зачеркивание. Элементы разметки могут содержать произвольное число других элементов разметки и текстовых элементов.
5.  Все классы должны реализовывать метод `toMarkdown([StringBuilder](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html))`, который должен генерировать [Markdown](https://ru.wikipedia.org/wiki/Markdown)\-разметку по следующим правилам:
    1.  текстовые элементы выводятся как есть;
    2.  выделенный текст окружается символами '`*`';
    3.  сильно выделенный текст окружается символами '`__`';
    4.  зачеркнутый текст окружается символами '`~`'.
6.  Следующий код должен успешно компилироваться:
    
        Paragraph paragraph = new Paragraph(List.of(
            new Strong(List.of(
                new Text("1"),
                new Strikeout(List.of(
                    new Text("2"),
                    new Emphasis(List.of(
                        new Text("3"),
                        new Text("4")
                    )),
                    new Text("5")
                )),
                new Text("6")
            ))
        ));
    
    Вызов `paragraph.toMarkdown(new StringBuilder())` должен заполнять переданный `StringBuilder` следующим содержимым:
    
        \_\_1~2\*34\*5~6\_\_
    
7.  Разработанные классы должны находиться в пакете `markup`.

### Реализация: [Markup](https://github.com/AndrewDanilin/ITMO-University/tree/main/prog-intro/java-solutions/markup)

Домашнее задание 8. Арифметические выражения
----

Нужно было разработать набор классов для вычисления арифметических выражений, которые могут содержать переменные.

### Реализация: [Expression](https://github.com/AndrewDanilin/ITMO-University/tree/main/prog-intro/java-solutions/expression)
