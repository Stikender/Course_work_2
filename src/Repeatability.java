public enum Repeatability {

    ONE_TIME_TASK("одноразовая"),
    DAILY_TASK("ежедневная"),
    WEEKLY_TASK("еженедельная"),
    MONTHLY_TASK("ежемесячная"),
    YEARLY_TASK("ежегодная");

    public String repeatabilityType;

    Repeatability(String repeatabilityType) {
        this.repeatabilityType = repeatabilityType;
    }

    public String getRepeatabilityType() {
        return repeatabilityType;
    }

    public void setRepeatabilityType(String repeatabilityType) {
        this.repeatabilityType = repeatabilityType;
    }

    @Override
    public String toString() {
        return getRepeatabilityType();
    }
}
