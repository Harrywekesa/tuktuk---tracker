package com.tuktuk.manager.ui.history;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/tuktuk/manager/ui/history/HistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/tuktuk/manager/data/repository/TukTukRepository;", "(Lcom/tuktuk/manager/data/repository/TukTukRepository;)V", "_selectedYearMonth", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "entries", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/tuktuk/manager/data/local/entity/DailyEntry;", "getEntries", "()Lkotlinx/coroutines/flow/StateFlow;", "selectedYearMonth", "getSelectedYearMonth", "getEntry", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nextMonth", "", "previousMonth", "app_debug"})
public final class HistoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.repository.TukTukRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedYearMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedYearMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> entries = null;
    
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.repository.TukTukRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedYearMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getEntries() {
        return null;
    }
    
    public final void previousMonth() {
    }
    
    public final void nextMonth() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getEntry(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.local.entity.DailyEntry> $completion) {
        return null;
    }
}