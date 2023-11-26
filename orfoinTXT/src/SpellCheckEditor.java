//Система проверки орфографии в тексте
//Дополнить ранее реализованный текстовый редактор так, чтобы во введенном тексте происходила автозамена какого-либо неправильно введенного слова на правильное.
// Проверку осуществить в отдельном потоке, чтобы не мешать пользователю осуществлять ввод.
// Запуск потока определить либо по времени, либо по нажатию клавиши пробела.



import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpellCheckEditor extends JFrame {
    private JTextArea textArea;
    private JLabel statusLabel;
    private Map<String, String> wordDictionary;

    public SpellCheckEditor() {
        setTitle("Spell Check Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wordDictionary = createWordDictionary();
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        statusLabel = new JLabel("Status: Ready");
        add(statusLabel, BorderLayout.SOUTH);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                startSpellCheck();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                startSpellCheck();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Style change, not needed for plain text
            }

        });
    }
//Метод `createWordDictionary()` создает словарь слов, которые будут автоматически заменяться при проверке орфографии.
// В данном случае, слова "hte" и "writting" будут заменяться на "the" и "writing" соответственно.
    private Map<String, String> createWordDictionary() {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("hte", "the");
        dictionary.put("writting", "writing");

        return dictionary;
    }
//Метод `startSpellCheck()` запускает проверку орфографии.
// Он создает объект `SwingWorker`, который выполняет проверку орфографии в фоновом режиме и обновляет статус проверки.
    private void startSpellCheck() {
        SwingWorker<Void, Void> spellCheckWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                spellCheck();
                return null;
            }

            @Override
            protected void done() {
                statusLabel.setText("Status: Готов");
            }
        };
        statusLabel.setText("Status: Поиск...");
        spellCheckWorker.execute();
    }
//Метод `spellCheck()` разбивает текст из `textArea` на отдельные слова и проверяет каждое слово на наличие в словаре `wordDictionary`.
// Если слово найдено в словаре, оно заменяется на соответствующее значение из словаря.
    private void spellCheck() {
        String text = textArea.getText();
        String[] words = text.split("\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (wordDictionary.containsKey(word)) {
                try {
                    int start = textArea.getLineStartOffset(textArea.getLineOfOffset(i));
                    int end = textArea.getLineEndOffset(textArea.getLineOfOffset(i));
                    textArea.replaceRange(wordDictionary.get(word), start, end);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpellCheckEditor editor = new SpellCheckEditor();
            editor.setVisible(true);
        });
    }
}
