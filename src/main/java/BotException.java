public class BotException extends Exception {
  public BotException(String msg) {
    super(msg);
  }
}

class InvalidCommandException extends BotException {
  public InvalidCommandException(String msg) {
    super(msg);
  }
}

class InvalidArgumentException extends BotException {
  public InvalidArgumentException(String msg) {
    super(msg);
  }
}

class IncompleteArgumentException extends BotException {
  public IncompleteArgumentException(String msg) {
    super(msg);
  }
}
