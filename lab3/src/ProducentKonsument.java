public class ProducentKonsument {
    public static void main(String[] args) {
        SharingData data = new SharingData();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String newData = "Data" + i;
                data.produce(newData);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data.consume();
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SharingData {
    private String data;
    private boolean isDataAvailable = false;

    synchronized String consume() {
        try {
            while (!isDataAvailable) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isDataAvailable = false;
        notify();
        printData("Odczytano: " + data);
        return data;
    }

    synchronized void produce(String newData) {
        try {
            while (isDataAvailable) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = newData;
        isDataAvailable = true;
        notify();
        printData("Wyprodukowano: " + data);
    }

    private void printData(String message) {
        System.out.println(message);
    }
}