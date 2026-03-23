package com.tuktuk.manager.ui.expenses;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0014J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0014J\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0014J\u000e\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006 "}, d2 = {"Lcom/tuktuk/manager/ui/expenses/AddExpenseViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/tuktuk/manager/data/repository/TukTukRepository;", "(Lcom/tuktuk/manager/data/repository/TukTukRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/tuktuk/manager/ui/expenses/AddExpenseState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "loadExpense", "expenseId", "", "save", "setCategory", "c", "", "setCost", "", "setDate", "d", "setDescription", "setMechanic", "m", "setNotes", "n", "setPaidFrom", "p", "app_debug"})
public final class AddExpenseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.repository.TukTukRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tuktuk.manager.ui.expenses.AddExpenseState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.expenses.AddExpenseState> uiState = null;
    
    public AddExpenseViewModel(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.repository.TukTukRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.expenses.AddExpenseState> getUiState() {
        return null;
    }
    
    public final void loadExpense(long expenseId) {
    }
    
    public final void setDate(@org.jetbrains.annotations.NotNull()
    java.lang.String d) {
    }
    
    public final void setCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String c) {
    }
    
    public final void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String d) {
    }
    
    public final void setMechanic(@org.jetbrains.annotations.NotNull()
    java.lang.String m) {
    }
    
    public final void setCost(double c) {
    }
    
    public final void setPaidFrom(@org.jetbrains.annotations.NotNull()
    java.lang.String p) {
    }
    
    public final void setNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String n) {
    }
    
    public final void save() {
    }
    
    public final void clearError() {
    }
}