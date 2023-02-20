package Diary;

public enum Repeatability {

    ONE_TIME_TASK("Одноразовая"),
    DAILY_TASK("Ежедневная"),
    WEEKLY_TASK("Еженедельная"),
    MONTHLY_TASK("Ежемесячная"),
    YEARLY_TASK("Ежегодная");

    public String repeatabilityType;

    Repeatability(String repeatabilityType) {
        this.repeatabilityType = repeatabilityType;
    }

    public String getRepeatabilityType() {
        return repeatabilityType;
    }

    @Override
    public String toString() {
        return getRepeatabilityType();
    }
}
