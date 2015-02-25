package ch.mgeggishorn.logger;


enum Level { DEBUG, INFO, WARN, ERROR }
public class Logger {
    private final Log log;
    private final String context;

    public Logger(Log log, String context) {
        this.log = log;
        this.context = context;
    }

    public void log(LogRecord record) {
        log.offer(record);
    }

    public void debug(String msg) {
        log(new LogRecord(Level.DEBUG, context, msg));
    }

    public void info(String msg) {
        log(new LogRecord(Level.INFO, context, msg));
    }

    public void warn(String msg) {
        log(new LogRecord(Level.WARN, context, msg));
    }

    public void error(String msg) {
        log(new LogRecord(Level.ERROR, context, msg));
    }

    public Log getLog() {
        return log;
    }
}







/*public class LogViewer extends Application {
    private final Random random = new Random(42);

    @Override
    public void start(Stage stage) throws Exception {
        Lorem  lorem  = new Lorem();
        Log    log    = new Log();
        Logger logger = new Logger(log, "main");

        logger.info("Hello");
        logger.warn("Don't pick up alien hitchhickers");

        for (int x = 0; x < 20; x++) {
            Thread generatorThread = new Thread(
                    () -> {
                        for (;;) {
                            logger.log(
                                    new LogRecord(
                                            lorem.nextLevel(),
                                            Thread.currentThread().getName(),
                                            lorem.nextString()
                                    )
                            );

                            try {
                                Thread.sleep(random.nextInt(1_000));
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    },
                    "log-gen-" + x
            );
            generatorThread.setDaemon(true);
            generatorThread.start();
        }

        LogView logView = new LogView(logger);
        logView.setPrefWidth(400);

        ChoiceBox<Level> filterLevel = new ChoiceBox<>(
                FXCollections.observableArrayList(
                        Level.values()
                )
        );
        filterLevel.getSelectionModel().select(Level.DEBUG);
        logView.filterLevelProperty().bind(
                filterLevel.getSelectionModel().selectedItemProperty()
        );

        ToggleButton showTimestamp = new ToggleButton("Show Timestamp");
        logView.showTimeStampProperty().bind(showTimestamp.selectedProperty());

        ToggleButton tail = new ToggleButton("Tail");
        logView.tailProperty().bind(tail.selectedProperty());

        ToggleButton pause = new ToggleButton("Pause");
        logView.pausedProperty().bind(pause.selectedProperty());

        Slider rate = new Slider(0.1, 60, 60);
        logView.refreshRateProperty().bind(rate.valueProperty());
        Label rateLabel = new Label();
        rateLabel.textProperty().bind(Bindings.format("Update: %.2f fps", rate.valueProperty()));
        rateLabel.setStyle("-fx-font-family: monospace;");
        VBox rateLayout = new VBox(rate, rateLabel);
        rateLayout.setAlignment(Pos.CENTER);

        HBox controls = new HBox(
                10,
                filterLevel,
                showTimestamp,
                tail,
                pause,
                rateLayout
        );
        controls.setMinHeight(HBox.USE_PREF_SIZE);

        VBox layout = new VBox(
                10,
                controls,
                logView
        );
        VBox.setVgrow(logView, Priority.ALWAYS);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(
            this.getClass().getResource("log-view.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }
}*/