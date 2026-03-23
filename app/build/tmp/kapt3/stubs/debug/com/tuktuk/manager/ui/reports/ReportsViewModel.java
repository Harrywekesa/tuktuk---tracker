package com.tuktuk.manager.ui.reports;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J<\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018H\u0002J<\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018H\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\u0006\u0010\"\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006%"}, d2 = {"Lcom/tuktuk/manager/ui/reports/ReportsViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/tuktuk/manager/data/repository/TukTukRepository;", "(Lcom/tuktuk/manager/data/repository/TukTukRepository;)V", "_reportState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/tuktuk/manager/ui/reports/ReportState;", "_selectedYearMonth", "", "reportState", "Lkotlinx/coroutines/flow/StateFlow;", "getReportState", "()Lkotlinx/coroutines/flow/StateFlow;", "selectedYearMonth", "getSelectedYearMonth", "buildExcel", "Ljava/io/File;", "context", "Landroid/content/Context;", "ym", "stats", "Lcom/tuktuk/manager/data/model/MonthlyStats;", "entries", "", "Lcom/tuktuk/manager/data/local/entity/DailyEntry;", "expenses", "Lcom/tuktuk/manager/data/local/entity/Expense;", "buildPdf", "generateExcel", "", "generatePdf", "nextMonth", "previousMonth", "resetState", "selectMonth", "yearMonth", "app_debug"})
public final class ReportsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.repository.TukTukRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedYearMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedYearMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tuktuk.manager.ui.reports.ReportState> _reportState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.reports.ReportState> reportState = null;
    
    public ReportsViewModel(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.repository.TukTukRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedYearMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.reports.ReportState> getReportState() {
        return null;
    }
    
    public final void selectMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth) {
    }
    
    public final void previousMonth() {
    }
    
    public final void nextMonth() {
    }
    
    public final void generatePdf(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void generateExcel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void resetState() {
    }
    
    private final java.io.File buildPdf(android.content.Context context, java.lang.String ym, com.tuktuk.manager.data.model.MonthlyStats stats, java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry> entries, java.util.List<com.tuktuk.manager.data.local.entity.Expense> expenses) {
        return null;
    }
    
    private final java.io.File buildExcel(android.content.Context context, java.lang.String ym, com.tuktuk.manager.data.model.MonthlyStats stats, java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry> entries, java.util.List<com.tuktuk.manager.data.local.entity.Expense> expenses) {
        return null;
    }
}