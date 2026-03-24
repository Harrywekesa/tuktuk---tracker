package com.tuktuk.manager.data.local.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u0000 S2\u00020\u0001:\u0002STB\u0081\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010@\u001a\u00020\u0003H\u00c6\u0003J\t\u0010A\u001a\u00020\u000fH\u00c6\u0003J\t\u0010B\u001a\u00020\u000fH\u00c6\u0003J\t\u0010C\u001a\u00020\u0003H\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u0005H\u00c6\u0003J\t\u0010F\u001a\u00020\u0005H\u00c6\u0003J\t\u0010G\u001a\u00020\u0005H\u00c6\u0003J\t\u0010H\u001a\u00020\tH\u00c6\u0003J\t\u0010I\u001a\u00020\tH\u00c6\u0003J\t\u0010J\u001a\u00020\tH\u00c6\u0003J\t\u0010K\u001a\u00020\tH\u00c6\u0003J\t\u0010L\u001a\u00020\u0005H\u00c6\u0003J\u008b\u0001\u0010M\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010N\u001a\u00020\u000f2\b\u0010O\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010P\u001a\u00020QH\u00d6\u0001J\t\u0010R\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0014\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u001b\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u001d\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010%\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u0018R\u0011\u0010\'\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u0018R\u0011\u0010)\u001a\u00020*8F\u00a2\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0011\u0010/\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b0\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0016R\u0011\u00101\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b2\u0010\u0018R\u0011\u00103\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b4\u0010\u0016R\u0011\u00105\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0011\u00108\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b9\u0010\u0018R\u0011\u0010:\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b;\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010\"R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\"R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010 \u00a8\u0006U"}, d2 = {"Lcom/tuktuk/manager/data/local/entity/DailyEntry;", "", "id", "", "date", "", "timeIn", "timeOut", "startOdometer", "", "endOdometer", "grossIncome", "actualFuelCost", "notes", "isSynced", "", "deductFloat", "createdAt", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDDLjava/lang/String;ZZJJ)V", "aboveMinimum", "getAboveMinimum", "()Z", "getActualFuelCost", "()D", "bankDeposit", "getBankDeposit", "businessKm", "getBusinessKm", "businessProfit", "getBusinessProfit", "getCreatedAt", "()J", "getDate", "()Ljava/lang/String;", "getDeductFloat", "getEndOdometer", "fuelPerKm", "getFuelPerKm", "fuelPercentage", "getFuelPercentage", "fuelStatus", "Lcom/tuktuk/manager/data/local/entity/DailyEntry$FuelStatus;", "getFuelStatus", "()Lcom/tuktuk/manager/data/local/entity/DailyEntry$FuelStatus;", "getGrossIncome", "getId", "incomePerKm", "getIncomePerKm", "maintenanceSave", "getMaintenanceSave", "meetsTarget", "getMeetsTarget", "netProfit", "getNetProfit", "getNotes", "ownerSalary", "getOwnerSalary", "riderPay", "getRiderPay", "getStartOdometer", "getTimeIn", "getTimeOut", "getUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "FuelStatus", "app_debug"})
@androidx.room.Entity(tableName = "daily_entries")
public final class DailyEntry {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String date = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String timeIn = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String timeOut = null;
    private final double startOdometer = 0.0;
    private final double endOdometer = 0.0;
    private final double grossIncome = 0.0;
    private final double actualFuelCost = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notes = null;
    private final boolean isSynced = false;
    private final boolean deductFloat = false;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    public static final double FLOAT_AMOUNT = 200.0;
    public static final double COMMUTE_KM = 10.0;
    public static final double SPLIT_RATIO = 0.25;
    public static final double DAILY_TARGET = 3000.0;
    public static final double MIN_GROSS = 2500.0;
    public static final double FUEL_GOOD_THRESHOLD = 0.25;
    public static final double FUEL_HIGH_THRESHOLD = 0.35;
    @org.jetbrains.annotations.NotNull()
    public static final com.tuktuk.manager.data.local.entity.DailyEntry.Companion Companion = null;
    
    public DailyEntry(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String timeIn, @org.jetbrains.annotations.NotNull()
    java.lang.String timeOut, double startOdometer, double endOdometer, double grossIncome, double actualFuelCost, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, boolean isSynced, boolean deductFloat, long createdAt, long updatedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeIn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeOut() {
        return null;
    }
    
    public final double getStartOdometer() {
        return 0.0;
    }
    
    public final double getEndOdometer() {
        return 0.0;
    }
    
    public final double getGrossIncome() {
        return 0.0;
    }
    
    public final double getActualFuelCost() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotes() {
        return null;
    }
    
    public final boolean isSynced() {
        return false;
    }
    
    public final boolean getDeductFloat() {
        return false;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public final double getBusinessKm() {
        return 0.0;
    }
    
    public final double getNetProfit() {
        return 0.0;
    }
    
    public final double getOwnerSalary() {
        return 0.0;
    }
    
    public final double getRiderPay() {
        return 0.0;
    }
    
    public final double getMaintenanceSave() {
        return 0.0;
    }
    
    public final double getBusinessProfit() {
        return 0.0;
    }
    
    public final double getBankDeposit() {
        return 0.0;
    }
    
    public final double getIncomePerKm() {
        return 0.0;
    }
    
    public final double getFuelPerKm() {
        return 0.0;
    }
    
    public final double getFuelPercentage() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.tuktuk.manager.data.local.entity.DailyEntry.FuelStatus getFuelStatus() {
        return null;
    }
    
    public final boolean getMeetsTarget() {
        return false;
    }
    
    public final boolean getAboveMinimum() {
        return false;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final long component12() {
        return 0L;
    }
    
    public final long component13() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.tuktuk.manager.data.local.entity.DailyEntry copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String timeIn, @org.jetbrains.annotations.NotNull()
    java.lang.String timeOut, double startOdometer, double endOdometer, double grossIncome, double actualFuelCost, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, boolean isSynced, boolean deductFloat, long createdAt, long updatedAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/tuktuk/manager/data/local/entity/DailyEntry$Companion;", "", "()V", "COMMUTE_KM", "", "DAILY_TARGET", "FLOAT_AMOUNT", "FUEL_GOOD_THRESHOLD", "FUEL_HIGH_THRESHOLD", "MIN_GROSS", "SPLIT_RATIO", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/tuktuk/manager/data/local/entity/DailyEntry$FuelStatus;", "", "(Ljava/lang/String;I)V", "NONE", "GOOD", "HIGH", "ALERT", "app_debug"})
    public static enum FuelStatus {
        /*public static final*/ NONE /* = new NONE() */,
        /*public static final*/ GOOD /* = new GOOD() */,
        /*public static final*/ HIGH /* = new HIGH() */,
        /*public static final*/ ALERT /* = new ALERT() */;
        
        FuelStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.tuktuk.manager.data.local.entity.DailyEntry.FuelStatus> getEntries() {
            return null;
        }
    }
}