document.addEventListener('DOMContentLoaded', function() {
    // Initialize Lucide icons
    // Assuming lucide is available globally or imported elsewhere.
    // If not, you might need to import it like this:
    // import * as lucide from 'lucide';
    // or include it via a script tag in your HTML.
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    } else {
        console.warn('Lucide icons not initialized. Ensure Lucide is properly included.');
    }

    // Sidebar toggle
    const sidebarToggle = document.getElementById('sidebar-toggle');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');

    if (sidebarToggle && sidebar && overlay) {
        sidebarToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            overlay.classList.toggle('active');
        });

        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
    }

    // Tab functionality
    const tabTriggers = document.querySelectorAll('.tab-trigger');
    const tabContents = document.querySelectorAll('.tab-content');

    tabTriggers.forEach(trigger => {
        trigger.addEventListener('click', () => {
            // Remove active class from all triggers and contents
            tabTriggers.forEach(t => t.classList.remove('active'));
            tabContents.forEach(c => c.classList.remove('active'));

            // Add active class to clicked trigger and corresponding content
            trigger.classList.add('active');
            const tabId = trigger.getAttribute('data-tab');
            const content = document.getElementById(tabId);
            if (content) {
                content.classList.add('active');
            }
        });
    });

    // Create charts
    createCharts();
});

function createCharts() {
    // Overview Chart (Bar Chart)
    const overviewChart = document.getElementById('overview-chart');
    if (overviewChart) {
        const data = [
            { month: "Jan", revenue: 2500 },
            { month: "Feb", revenue: 3500 },
            { month: "Mar", revenue: 3000 },
            { month: "Apr", revenue: 4500 },
            { month: "May", revenue: 4000 },
            { month: "Jun", revenue: 5000 },
            { month: "Jul", revenue: 6000 },
            { month: "Aug", revenue: 5500 },
            { month: "Sep", revenue: 7000 },
            { month: "Oct", revenue: 8000 },
            { month: "Nov", revenue: 7500 },
            { month: "Dec", revenue: 9000 },
        ];

        const maxRevenue = Math.max(...data.map(item => item.revenue));

        // Clear any existing content
        overviewChart.innerHTML = '';

        // Create the chart
        data.forEach(item => {
            const barContainer = document.createElement('div');
            barContainer.className = 'relative flex h-full flex-1 flex-col justify-end';

            const bar = document.createElement('div');
            bar.className = 'w-full rounded-md bg-primary-color hover:opacity-80 transition-opacity';
            bar.style.height = `${(item.revenue / maxRevenue) * 100}%`;
            bar.style.backgroundColor = 'var(--primary-color)';

            const label = document.createElement('span');
            label.className = 'mt-1.5 text-center text-xs text-muted';
            label.textContent = item.month;

            barContainer.appendChild(bar);
            barContainer.appendChild(label);
            overviewChart.appendChild(barContainer);
        });
    }

    // Ticket Volume Chart
    const ticketVolumeChart = document.getElementById('ticket-volume-chart');
    if (ticketVolumeChart) {
        const data = [
            { day: "Mon", count: 15 },
            { day: "Tue", count: 22 },
            { day: "Wed", count: 18 },
            { day: "Thu", count: 25 },
            { day: "Fri", count: 30 },
            { day: "Sat", count: 12 },
            { day: "Sun", count: 8 },
        ];

        const maxCount = Math.max(...data.map(item => item.count));

        // Clear any existing content
        ticketVolumeChart.innerHTML = '';

        // Create the chart
        data.forEach(item => {
            const barContainer = document.createElement('div');
            barContainer.className = 'relative flex h-full flex-1 flex-col justify-end';

            const bar = document.createElement('div');
            bar.className = 'w-full rounded-md hover:opacity-80 transition-opacity';
            bar.style.height = `${(item.count / maxCount) * 100}%`;
            bar.style.backgroundColor = 'var(--info-color)';

            const label = document.createElement('span');
            label.className = 'mt-1.5 text-center text-xs text-muted';
            label.textContent = item.day;

            barContainer.appendChild(bar);
            barContainer.appendChild(label);
            ticketVolumeChart.appendChild(barContainer);
        });
    }

    // Activity Chart
    const activityChart = document.getElementById('activity-chart');
    if (activityChart) {
        const data = [
            { hour: "9AM", count: 45 },
            { hour: "10AM", count: 78 },
            { hour: "11AM", count: 95 },
            { hour: "12PM", count: 65 },
            { hour: "1PM", count: 42 },
            { hour: "2PM", count: 85 },
            { hour: "3PM", count: 92 },
            { hour: "4PM", count: 75 },
        ];

        const maxCount = Math.max(...data.map(item => item.count));

        // Clear any existing content
        activityChart.innerHTML = '';

        // Create the chart
        data.forEach(item => {
            const barContainer = document.createElement('div');
            barContainer.className = 'relative flex h-full flex-1 flex-col justify-end';

            const bar = document.createElement('div');
            bar.className = 'w-full rounded-md hover:opacity-80 transition-opacity';
            bar.style.height = `${(item.count / maxCount) * 100}%`;
            bar.style.backgroundColor = 'var(--success-color)';

            const label = document.createElement('span');
            label.className = 'mt-1.5 text-center text-xs text-muted';
            label.textContent = item.hour;

            barContainer.appendChild(bar);
            barContainer.appendChild(label);
            activityChart.appendChild(barContainer);
        });
    }

    // Sales Chart
    const salesChart = document.getElementById('sales-chart');
    if (salesChart) {
        const data = [
            { day: "Mon", sales: 1200 },
            { day: "Tue", sales: 1800 },
            { day: "Wed", sales: 1400 },
            { day: "Thu", sales: 2200 },
            { day: "Fri", sales: 2500 },
            { day: "Sat", sales: 1900 },
            { day: "Sun", sales: 1100 },
        ];

        const maxSales = Math.max(...data.map(item => item.sales));

        // Clear any existing content
        salesChart.innerHTML = '';

        // Create the chart
        data.forEach(item => {
            const barContainer = document.createElement('div');
            barContainer.className = 'relative flex h-full flex-1 flex-col justify-end';

            const bar = document.createElement('div');
            bar.className = 'w-full rounded-md hover:opacity-80 transition-opacity';
            bar.style.height = `${(item.sales / maxSales) * 100}%`;
            bar.style.backgroundColor = 'var(--primary-color)';

            const label = document.createElement('span');
            label.className = 'mt-1.5 text-center text-xs text-muted';
            label.textContent = item.day;

            barContainer.appendChild(bar);
            barContainer.appendChild(label);
            salesChart.appendChild(barContainer);
        });
    }
}