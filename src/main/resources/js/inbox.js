document.addEventListener('DOMContentLoaded', function() {
    var cols = {},
        messageIsOpen = false;

    cols.showOverlay = function() {
        document.body.classList.add('show-main-overlay');
    };

    cols.hideOverlay = function() {
        document.body.classList.remove('show-main-overlay');
    };

    cols.showMessage = function() {
        document.body.classList.add('show-message');
        messageIsOpen = true;
    };

    cols.hideMessage = function() {
        document.body.classList.remove('show-message');
        document.querySelectorAll('#main .message-list li').forEach(function(item) {
            item.classList.remove('active');
        });
        messageIsOpen = false;
    };

    cols.showSidebar = function() {
        document.body.classList.add('show-sidebar');
    };

    cols.hideSidebar = function() {
        document.body.classList.remove('show-sidebar');
    };

    // Show sidebar when trigger is clicked
    document.querySelectorAll('.trigger-toggle-sidebar').forEach(function(trigger) {
        trigger.addEventListener('click', function() {
            cols.showSidebar();
            cols.showOverlay();
        });
    });

    document.querySelectorAll('.trigger-message-close').forEach(function(trigger) {
        trigger.addEventListener('click', function() {
            cols.hideMessage();
            cols.hideOverlay();
        });
    });

    // When you click on a message, show it
    document.querySelectorAll('#main .message-list li').forEach(function(item) {
        item.addEventListener('click', function(e) {
            var target = e.target;
            
            if (target.tagName === 'LABEL') {
                item.classList.toggle('selected');
            } else {
                if (messageIsOpen && item.classList.contains('active')) {
                    cols.hideMessage();
                    cols.hideOverlay();
                } else {
                    if (messageIsOpen) {
                        cols.hideMessage();
                        item.classList.add('active');
                        setTimeout(function() {
                            cols.showMessage();
                        }, 300);
                    } else {
                        item.classList.add('active');
                        cols.showMessage();
                    }
                    cols.showOverlay();
                }
            }
        });
    });

    // This will prevent click from triggering twice when clicking checkbox/label
    document.querySelectorAll('input[type=checkbox]').forEach(function(checkbox) {
        checkbox.addEventListener('click', function(e) {
            e.stopImmediatePropagation();
        });
    });

    // When you click the overlay, close everything
    document.querySelector('#main > .overlay').addEventListener('click', function() {
        cols.hideOverlay();
        cols.hideMessage();
        cols.hideSidebar();
    });

    // Enable sexy scrollbars (assuming you have a function to handle this)
    document.querySelectorAll('.nano').forEach(function(nano) {
        // Assuming nanoScroller is a function to initialize the scrollbars
        nanoScroller(nano);
    });

    // Disable links
    document.querySelectorAll('a').forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault();
        });
    });

    // Search box responsive stuff
    var searchBoxInput = document.querySelector('.search-box input');
    if (searchBoxInput) {
        searchBoxInput.addEventListener('focus', function() {
            if (window.innerWidth <= 1360) {
                cols.hideMessage();
            }
        });
    }
});
