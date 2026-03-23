package com.tuktuk.manager.ui.analytics;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0016J\u001a\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u000fH\u0002J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u000fH\u0002J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020&H\u0002J\u0010\u0010\'\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010(\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\u0016\u0010,\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\u0010\u0010-\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006."}, d2 = {"Lcom/tuktuk/manager/ui/analytics/AnalyticsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/tuktuk/manager/databinding/FragmentAnalyticsBinding;", "binding", "getBinding", "()Lcom/tuktuk/manager/databinding/FragmentAnalyticsBinding;", "viewModel", "Lcom/tuktuk/manager/ui/analytics/AnalyticsViewModel;", "getViewModel", "()Lcom/tuktuk/manager/ui/analytics/AnalyticsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindStatsCards", "", "stats", "Lcom/tuktuk/manager/data/model/MonthlyStats;", "observeData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupBarChart", "chart", "Lcom/github/mikephil/charting/charts/BarChart;", "setupCharts", "setupLineChart", "Lcom/github/mikephil/charting/charts/LineChart;", "setupMonthNavigation", "setupPieChart", "Lcom/github/mikephil/charting/charts/PieChart;", "updateFuelBars", "updateFuelStatusBars", "entries", "", "Lcom/tuktuk/manager/data/local/entity/DailyEntry;", "updateIncomeLineChart", "updatePieChart", "app_debug"})
public final class AnalyticsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.tuktuk.manager.databinding.FragmentAnalyticsBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public AnalyticsFragment() {
        super();
    }
    
    private final com.tuktuk.manager.databinding.FragmentAnalyticsBinding getBinding() {
        return null;
    }
    
    private final com.tuktuk.manager.ui.analytics.AnalyticsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupMonthNavigation() {
    }
    
    private final void observeData() {
    }
    
    private final void bindStatsCards(com.tuktuk.manager.data.model.MonthlyStats stats) {
    }
    
    private final void setupCharts() {
    }
    
    private final void setupLineChart(com.github.mikephil.charting.charts.LineChart chart) {
    }
    
    private final void setupPieChart(com.github.mikephil.charting.charts.PieChart chart) {
    }
    
    private final void setupBarChart(com.github.mikephil.charting.charts.BarChart chart) {
    }
    
    private final void updateIncomeLineChart(java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry> entries) {
    }
    
    private final void updatePieChart(com.tuktuk.manager.data.model.MonthlyStats stats) {
    }
    
    private final void updateFuelBars(com.tuktuk.manager.data.model.MonthlyStats stats) {
    }
    
    private final void updateFuelStatusBars(java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry> entries) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}