import java.io.*;

public class PopThread implements Runnable {
    private final String[] files;
    private static StringBuilder resultContent = new StringBuilder();

    public PopThread(String[] files) {
        this.files = files;
    }

    @Override
    public void run() {
        for (String file : files) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                synchronized (PopThread.class) {
                    resultContent.append(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void printContents(String filename) {
        String[] sortedResultContent = resultContent.toString().split("\n");
        sortContent(sortedResultContent);

        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // Append mode
            for (String line : sortedResultContent) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            // Reset the resultContent for the next iteration
            resultContent = new StringBuilder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sortContent(String[] content) {
        if (content == null || content.length <= 1) return;

        for (int i = 0; i < content.length; i++) {
            for (int j = i + 1; j < content.length; j++) {
                if (compareContent(content[i], content[j]) > 0) {
                    String temp = content[i];
                    content[i] = content[j];
                    content[j] = temp;
                }
            }
        }
    }

    private static int compareContent(String o1, String o2) {
        int order1 = getOrder(o1);
        int order2 = getOrder(o2);
        return Integer.compare(order1, order2);
    }

    private static int getOrder(String line) {
        String[] parts = line.split("#|/");
        if (parts.length < 2) {
            // Handle the case where splitting didn't provide at least two parts
            return 0; // or any default value you prefer
        }
        return Integer.parseInt(parts[1]);
    }



    public static void main(String[] args) {
        String[] filesOne = {"1831-06-01.txt", "2003-08-27.txt"};
        String[] filesTwo = {"1961-04-12.txt", "1972-12-11.txt"};
        int numIterations = 3; // Repeat the process three times

        for (int i = 0; i < numIterations; i++) {
            System.out.println("Iteration: " + (i + 1));
            PopThread popRunnableOne = new PopThread(filesOne);
            PopThread popRunnableTwo = new PopThread(filesTwo);
            Thread threadOne = new Thread(popRunnableOne);
            Thread threadTwo = new Thread(popRunnableTwo);
            threadOne.start();
            threadTwo.start();
            try {
                threadOne.join();
                threadTwo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printContents("result.txt");
        }
    }
}
