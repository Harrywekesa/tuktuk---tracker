package com.tuktuk.manager.ui.dailyentry;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0010J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0018J\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0018J\u000e\u0010#\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0018R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006$"}, d2 = {"Lcom/tuktuk/manager/ui/dailyentry/DailyEntryViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/tuktuk/manager/data/repository/TukTukRepository;", "(Lcom/tuktuk/manager/data/repository/TukTukRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/tuktuk/manager/ui/dailyentry/DailyEntryUiState;", "livePreview", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/tuktuk/manager/ui/dailyentry/LivePreview;", "getLivePreview", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "clearError", "", "deleteEntry", "loadEntry", "entryId", "", "saveEntry", "setDate", "date", "", "setEndOdo", "value", "", "setFuel", "setGross", "setNotes", "text", "setStartOdo", "setTimeIn", "time", "setTimeOut", "app_debug"})
public final class DailyEntryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.repository.TukTukRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tuktuk.manager.ui.dailyentry.DailyEntryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.dailyentry.DailyEntryUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.dailyentry.LivePreview> livePreview = null;
    
    public DailyEntryViewModel(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.repository.TukTukRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.dailyentry.DailyEntryUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.dailyentry.LivePreview> getLivePreview() {
        return null;
    }
    
    public final void loadEntry(long entryId) {
    }
    
    public final void setDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
    }
    
    public final void setTimeIn(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
    }
    
    public final void setTimeOut(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
    }
    
    public final void setStartOdo(double value) {
    }
    
    public final void setEndOdo(double value) {
    }
    
    public final void setGross(double value) {
    }
    
    public final void setFuel(double value) {
    }
    
    public final void setNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void saveEntry() {
    }
    
    public final void deleteEntry() {
    }
    
    public final void clearError() {
    }
}