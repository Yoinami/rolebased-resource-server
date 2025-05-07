document.addEventListener('DOMContentLoaded', function() {
    // Initialize Lucide icons
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    } else {
        console.warn('Lucide icons not initialized. Ensure Lucide is properly included.');
    }

    // Mobile menu toggle
    const mobileMenuToggle = document.getElementById('mobile-menu-toggle');
    const mobileNav = document.getElementById('mobile-nav');

    if (mobileMenuToggle && mobileNav) {
        mobileMenuToggle.addEventListener('click', function() {
            mobileNav.classList.toggle('active');
        });
    }

    // Cart sidebar
    const cartButton = document.getElementById('cart-button');
    const cartSidebar = document.getElementById('cart-sidebar');
    const closeCart = document.getElementById('close-cart');
    const overlay = document.getElementById('overlay');
    const startShopping = document.getElementById('start-shopping');

    function openCart() {
        cartSidebar.classList.add('active');
        overlay.classList.add('active');
        document.body.style.overflow = 'hidden';
    }

    function closeCartSidebar() {
        cartSidebar.classList.remove('active');
        overlay.classList.remove('active');
        document.body.style.overflow = '';
    }

    if (cartButton && cartSidebar && closeCart && overlay) {
        cartButton.addEventListener('click', openCart);
        closeCart.addEventListener('click', closeCartSidebar);
        overlay.addEventListener('click', closeCartSidebar);

        if (startShopping) {
            startShopping.addEventListener('click', function() {
                closeCartSidebar();
                // Scroll to products section
                document.getElementById('products').scrollIntoView({ behavior: 'smooth' });
            });
        }
    }

    // Product filtering
    const filterButtons = document.querySelectorAll('.filter-button');
    const sortSelect = document.getElementById('sort-select');

    // Load products
    loadProducts();

    // Filter products when buttons are clicked
    if (filterButtons.length > 0) {
        filterButtons.forEach(button => {
            button.addEventListener('click', function() {
                // Remove active class from all buttons
                filterButtons.forEach(btn => btn.classList.remove('active'));
                // Add active class to clicked button
                this.classList.add('active');

                // Filter products
                const category = this.getAttribute('data-category');
                filterProducts(category);
            });
        });
    }

    // Sort products when select changes
    if (sortSelect) {
        sortSelect.addEventListener('change', function() {
            sortProducts(this.value);
        });
    }

    // Load more products
    const loadMoreBtn = document.getElementById('load-more-btn');
    if (loadMoreBtn) {
        loadMoreBtn.addEventListener('click', function() {
            loadMoreProducts();
        });
    }
});

// Product data
const products = [
    {
        id: 1,
        title: 'Casual Cotton T-Shirt',
        category: 'clothing',
        price: 24.99,
        originalPrice: 29.99,
        image: 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=880&q=80',
        badge: 'sale',
        isNew: false
    },
    {
        id: 2,
        title: 'Classic Denim Jacket',
        category: 'clothing',
        price: 59.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1551537482-f2075a1d41f2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=880&q=80',
        badge: null,
        isNew: true
    },
    {
        id: 3,
        title: 'Running Sneakers',
        category: 'shoes',
        price: 89.99,
        originalPrice: 99.99,
        image: 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80',
        badge: 'sale',
        isNew: false
    },
    {
        id: 4,
        title: 'Leather Crossbody Bag',
        category: 'accessories',
        price: 49.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1590874103328-eac38a683ce7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=876&q=80',
        badge: null,
        isNew: false
    },
    {
        id: 5,
        title: 'Summer Floral Dress',
        category: 'clothing',
        price: 39.99,
        originalPrice: 49.99,
        image: 'https://images.unsplash.com/photo-1612336307429-8a898d10e223?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80',
        badge: 'sale',
        isNew: false
    },
    {
        id: 6,
        title: 'Classic Aviator Sunglasses',
        category: 'accessories',
        price: 19.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1577803645773-f96470509666?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80',
        badge: null,
        isNew: true
    },
    {
        id: 7,
        title: 'Casual Slip-On Loafers',
        category: 'shoes',
        price: 69.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1560343090-f0409e92791a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=764&q=80',
        badge: null,
        isNew: false
    },
    {
        id: 8,
        title: 'Slim Fit Chino Pants',
        category: 'clothing',
        price: 44.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1624378439575-d8705ad7ae80?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=697&q=80',
        badge: null,
        isNew: false
    }
];

// More products for "load more" functionality
const moreProducts = [
    {
        id: 9,
        title: 'Vintage Leather Watch',
        category: 'accessories',
        price: 79.99,
        originalPrice: 99.99,
        image: 'https://images.unsplash.com/photo-1524805444758-089113d48a6d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=688&q=80',
        badge: 'sale',
        isNew: false
    },
    {
        id: 10,
        title: 'Hooded Sweatshirt',
        category: 'clothing',
        price: 34.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1556821840-3a63f95609a7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80',
        badge: null,
        isNew: true
    },
    {
        id: 11,
        title: 'Canvas High-Top Sneakers',
        category: 'shoes',
        price: 54.99,
        originalPrice: 64.99,
        image: 'https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=698&q=80',
        badge: 'sale',
        isNew: false
    },
    {
        id: 12,
        title: 'Beaded Bracelet Set',
        category: 'accessories',
        price: 14.99,
        originalPrice: null,
        image: 'https://images.unsplash.com/photo-1611652022419-a9419f74343d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fA%3D%3D&auto=format&fit=crop&w=688&q=80',
        badge: null,
        isNew: false
    }
];

// Cart data
let cart = [];

// Current displayed products
let currentProducts = [...products];
let currentCategory = 'all';
let currentSort = 'featured';
let productsLoaded = false;

// Load initial products
function loadProducts() {
    const productsGrid = document.getElementById('products-grid');
    if (!productsGrid) return;

    productsGrid.innerHTML = '';

    currentProducts.forEach(product => {
        productsGrid.appendChild(createProductCard(product));
    });
}

// Create product card
function createProductCard(product) {
    const card = document.createElement('div');
    card.className = 'product-card';

    let badgeHTML = '';
    if (product.badge === 'sale') {
        badgeHTML = `<span class="product-badge sale">Sale</span>`;
    } else if (product.isNew) {
        badgeHTML = `<span class="product-badge new">New</span>`;
    }

    let priceHTML = '';
    if (product.originalPrice) {
        priceHTML = `
      <div class="product-price">
        <span class="current-price">$${product.price.toFixed(2)}</span>
        <span class="original-price">$${product.originalPrice.toFixed(2)}</span>
      </div>
    `;
    } else {
        priceHTML = `
      <div class="product-price">
        <span class="current-price">$${product.price.toFixed(2)}</span>
      </div>
    `;
    }

    card.innerHTML = `
    <div class="product-image">
      ${badgeHTML}
      <button class="product-wishlist" data-id="${product.id}">
        <i data-lucide="heart"></i>
      </button>
      <img src="${product.image}" alt="${product.title}">
    </div>
    <div class="product-info">
      <div class="product-category">${product.category}</div>
      <h3 class="product-title">${product.title}</h3>
      ${priceHTML}
      <div class="product-actions">
        <button class="add-to-cart" data-id="${product.id}">Add to Cart</button>
        <button class="quick-view" data-id="${product.id}">
          <i data-lucide="eye"></i>
        </button>
      </div>
    </div>
  `;

    // Initialize icons in the newly created card
    if (typeof lucide !== 'undefined') {
        lucide.createIcons({
            icons: {
                heart: card.querySelector('[data-lucide="heart"]'),
                eye: card.querySelector('[data-lucide="eye"]')
            }
        });
    }

    // Add event listeners
    const addToCartBtn = card.querySelector('.add-to-cart');
    if (addToCartBtn) {
        addToCartBtn.addEventListener('click', function() {
            const productId = parseInt(this.getAttribute('data-id'));
            addToCart(productId);
        });
    }

    const wishlistBtn = card.querySelector('.product-wishlist');
    if (wishlistBtn) {
        wishlistBtn.addEventListener('click', function() {
            this.classList.toggle('active');
        });
    }

    return card;
}

// Filter products by category
function filterProducts(category) {
    currentCategory = category;

    if (category === 'all') {
        currentProducts = [...products];
    } else {
        currentProducts = products.filter(product => product.category === category);
    }

    // Apply current sort
    sortProducts(currentSort);

    // Reset "load more" state
    productsLoaded = false;
    const loadMoreBtn = document.getElementById('load-more-btn');
    if (loadMoreBtn) {
        loadMoreBtn.style.display = 'inline-block';
    }
}

// Sort products
function sortProducts(sortType) {
    currentSort = sortType;

    switch (sortType) {
        case 'price-low':
            currentProducts.sort((a, b) => a.price - b.price);
            break;
        case 'price-high':
            currentProducts.sort((a, b) => b.price - a.price);
            break;
        case 'newest':
            currentProducts.sort((a, b) => (a.isNew === b.isNew) ? 0 : a.isNew ? -1 : 1);
            break;
        default: // featured
            // No sorting needed, products are already in featured order
            break;
    }

    loadProducts();
}

// Load more products
function loadMoreProducts() {
    if (productsLoaded) return;

    const productsGrid = document.getElementById('products-grid');
    if (!productsGrid) return;

    // Filter more products based on current category
    let additionalProducts;
    if (currentCategory === 'all') {
        additionalProducts = [...moreProducts];
    } else {
        additionalProducts = moreProducts.filter(product => product.category === currentCategory);
    }

    // Add to current products
    currentProducts = [...currentProducts, ...additionalProducts];

    // Apply current sort
    sortProducts(currentSort);

    // Hide load more button
    const loadMoreBtn = document.getElementById('load-more-btn');
    if (loadMoreBtn) {
        loadMoreBtn.style.display = 'none';
    }

    productsLoaded = true;
}

// Add product to cart
function addToCart(productId) {
    // Find product
    const product = [...products, ...moreProducts].find(p => p.id === productId);
    if (!product) return;

    // Check if product is already in cart
    const existingItem = cart.find(item => item.id === productId);

    if (existingItem) {
        // Increase quantity
        existingItem.quantity += 1;
    } else {
        // Add new item
        cart.push({
            id: product.id,
            title: product.title,
            price: product.price,
            image: product.image,
            quantity: 1
        });
    }

    // Update cart UI
    updateCartUI();

    // Show cart
    const cartSidebar = document.getElementById('cart-sidebar');
    const overlay = document.getElementById('overlay');
    if (cartSidebar && overlay) {
        cartSidebar.classList.add('active');
        overlay.classList.add('active');
        document.body.style.overflow = 'hidden';
    }
}

// Update cart UI
function updateCartUI() {
    // Update cart count
    const cartCount = document.querySelector('.cart-count');
    if (cartCount) {
        const totalItems = cart.reduce((total, item) => total + item.quantity, 0);
        cartCount.textContent = totalItems;
    }

    // Update cart items
    const cartItems = document.getElementById('cart-items');
    if (!cartItems) return;

    if (cart.length === 0) {
        // Show empty cart message
        cartItems.innerHTML = `
      <div class="empty-cart">
        <i data-lucide="shopping-bag" class="empty-cart-icon"></i>
        <p>Your cart is empty</p>
        <button class="btn btn-primary" id="start-shopping">Start Shopping</button>
      </div>
    `;

        // Initialize icons
        if (typeof lucide !== 'undefined') {
            lucide.createIcons({
                icons: {
                    'shopping-bag': cartItems.querySelector('[data-lucide="shopping-bag"]')
                }
            });
        }

        // Add event listener to start shopping button
        const startShopping = cartItems.querySelector('#start-shopping');
        if (startShopping) {
            startShopping.addEventListener('click', function() {
                // Close cart
                const cartSidebar = document.getElementById('cart-sidebar');
                const overlay = document.getElementById('overlay');
                if (cartSidebar && overlay) {
                    cartSidebar.classList.remove('active');
                    overlay.classList.remove('active');
                    document.body.style.overflow = '';
                }

                // Scroll to products section
                document.getElementById('products').scrollIntoView({ behavior: 'smooth' });
            });
        }
    } else {
        // Show cart items
        cartItems.innerHTML = '';

        cart.forEach(item => {
            const cartItem = document.createElement('div');
            cartItem.className = 'cart-item';

            cartItem.innerHTML = `
        <div class="cart-item-image">
          <img src="${item.image}" alt="${item.title}">
        </div>
        <div class="cart-item-details">
          <h4 class="cart-item-title">${item.title}</h4>
          <div class="cart-item-price">$${(item.price * item.quantity).toFixed(2)}</div>
          <div class="cart-item-quantity">
            <button class="quantity-btn decrease" data-id="${item.id}">
              <i data-lucide="minus"></i>
            </button>
            <span>${item.quantity}</span>
            <button class="quantity-btn increase" data-id="${item.id}">
              <i data-lucide="plus"></i>
            </button>
          </div>
        </div>
        <button class="cart-item-remove" data-id="${item.id}">
          <i data-lucide="trash-2"></i>
        </button>
      `;

            cartItems.appendChild(cartItem);

            // Initialize icons
            if (typeof lucide !== 'undefined') {
                lucide.createIcons({
                    icons: {
                        minus: cartItem.querySelector('[data-lucide="minus"]'),
                        plus: cartItem.querySelector('[data-lucide="plus"]'),
                        'trash-2': cartItem.querySelector('[data-lucide="trash-2"]')
                    }
                });
            }
        });

        // Add event listeners to cart item buttons
        const decreaseButtons = cartItems.querySelectorAll('.quantity-btn.decrease');
        const increaseButtons = cartItems.querySelectorAll('.quantity-btn.increase');
        const removeButtons = cartItems.querySelectorAll('.cart-item-remove');

        decreaseButtons.forEach(button => {
            button.addEventListener('click', function() {
                const id = parseInt(this.getAttribute('data-id'));
                decreaseQuantity(id);
            });
        });

        increaseButtons.forEach(button => {
            button.addEventListener('click', function() {
                const id = parseInt(this.getAttribute('data-id'));
                increaseQuantity(id);
            });
        });

        removeButtons.forEach(button => {
            button.addEventListener('click', function() {
                const id = parseInt(this.getAttribute('data-id'));
                removeFromCart(id);
            });
        });
    }

    // Update cart total
    updateCartTotal();
}

// Update cart total
function updateCartTotal() {
    const cartTotal = document.getElementById('cart-total-amount');
    if (!cartTotal) return;

    const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    cartTotal.textContent = `$${total.toFixed(2)}`;
}

// Increase item quantity
function increaseQuantity(id) {
    const item = cart.find(item => item.id === id);
    if (item) {
        item.quantity += 1;
        updateCartUI();
    }
}

// Decrease item quantity
function decreaseQuantity(id) {
    const item = cart.find(item => item.id === id);
    if (item) {
        item.quantity -= 1;

        if (item.quantity <= 0) {
            removeFromCart(id);
        } else {
            updateCartUI();
        }
    }
}

// Remove item from cart
function removeFromCart(id) {
    cart = cart.filter(item => item.id !== id);
    updateCartUI();
}