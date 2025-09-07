/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      fontFamily: {
        'chocolate-classical': ['"Chocolate Classical Sans"', 'sans-serif'],
      },
      colors: {
        'bank-blue': {
          100: '#71c7ec',
          200: '#1ebbd7',
          300: '#189ad3',
          400: '#107dac',
          500: '#005073',
        }
      },
    },
  },
  plugins: [],
}

