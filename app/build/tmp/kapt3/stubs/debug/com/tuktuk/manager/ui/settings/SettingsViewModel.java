package com.tuktuk.manager.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lcom/tuktuk/manager/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/tuktuk/manager/data/repository/TukTukRepository;", "(Lcom/tuktuk/manager/data/repository/TukTukRepository;)V", "_syncState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/tuktuk/manager/ui/settings/SyncState;", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/tuktuk/manager/data/local/entity/AppSettings;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "syncState", "getSyncState", "importLegacyData", "", "jsonEntries", "", "jsonExpenses", "resetSyncState", "saveSettings", "setTheme", "mode", "syncNow", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.repository.TukTukRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.data.local.entity.AppSettings> settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tuktuk.manager.ui.settings.SyncState> _syncState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.settings.SyncState> syncState = null;
    
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.repository.TukTukRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.data.local.entity.AppSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tuktuk.manager.ui.settings.SyncState> getSyncState() {
        return null;
    }
    
    public final void saveSettings(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.AppSettings settings) {
    }
    
    public final void syncNow() {
    }
    
    public final void setTheme(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    public final void importLegacyData(@org.jetbrains.annotations.NotNull()
    java.lang.String jsonEntries, @org.jetbrains.annotations.NotNull()
    java.lang.String jsonExpenses) {
    }
    
    public final void resetSyncState() {
    }
}