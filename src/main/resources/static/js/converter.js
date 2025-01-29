function isRomanNumeral(input) {
    // Valid Roman numeral pattern: Only M, D, C, L, X, V, I allowed
    // Checks for valid subtractive combinations
    const romanPattern = /^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/i;
    return romanPattern.test(input);
}

function isValidNumber(input) {
    const number = parseInt(input);
    return !isNaN(number) && number >= 1 && number <= 3999 && /^\d+$/.test(input);
}

function validateInput(input) {
    // Remove any whitespace
    input = input.trim();
    
    if (input === '') {
        clearResults();
        return null;
    }

    // Check if it's a valid Roman numeral
    if (isRomanNumeral(input)) {
        return { type: 'roman', value: input.toUpperCase() };
    }

    // Check if it's a valid number
    if (isValidNumber(input)) {
        return { type: 'number', value: parseInt(input) };
    }

    return { type: 'error', value: input };
}

function convertOnType(input) {
    const validation = validateInput(input);
    
    if (!validation) {
        return;
    }

    if (validation.type === 'error') {
        showError('Please enter a valid Roman numeral (I to MMMCMXCIX) or number (1-3999)');
        return;
    }

    convert(validation);
}

function clearResults() {
    document.getElementById('arabicResult').textContent = '';
    document.getElementById('romanResult').textContent = '';
    document.getElementById('numberInput').classList.remove('is-invalid');
    hideError();
}

function showError(message) {
    const input = document.getElementById('numberInput');
    input.classList.add('is-invalid');
    
    let errorDiv = document.querySelector('.error-text');
    if (!errorDiv) {
        errorDiv = document.createElement('div');
        errorDiv.className = 'error-text';
        input.parentNode.appendChild(errorDiv);
    }
    errorDiv.textContent = message;
}

function hideError() {
    const errorDiv = document.querySelector('.error-text');
    if (errorDiv) {
        errorDiv.remove();
    }
}

function convert(validation) {
    if (validation.type === 'number') {
        // Convert number to Roman
        fetch(`/api/toRoman?number=${validation.value}`, {
            method: 'POST'
        })
            .then(response => response.text())
            .then(result => {
                if (result.includes('error')) {
                    showError('Number must be between 1 and 3999');
                } else {
                    document.getElementById('arabicResult').textContent = `Arabic: ${validation.value}`;
                    document.getElementById('romanResult').textContent = `Roman: ${result}`;
                    hideError();
                }
            })
            .catch(() => {
                showError('Error converting number. Please try again.');
            });
    } else if (validation.type === 'roman') {
        // Convert Roman to number
        fetch(`/api/fromRoman?roman=${validation.value}`, {
            method: 'POST'
        })
            .then(response => response.text())
            .then(result => {
                if (result.includes('error')) {
                    showError('Invalid Roman numeral format');
                } else {
                    document.getElementById('arabicResult').textContent = `Arabic: ${result}`;
                    document.getElementById('romanResult').textContent = `Roman: ${validation.value}`;
                    hideError();
                }
            })
            .catch(() => {
                showError('Error converting Roman numeral. Please try again.');
            });
    }
}
