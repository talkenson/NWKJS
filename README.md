# NetJS

## About

Addon for KubeJS that provides direct HTTP requests. This fork removes the safety checks of the original mod and exposes a simple API that lets scripts contact any URL. Handling responses and validating data is completely up to the script author.

Built for Minecraft 1.21.1 on NeoForge using Java 21.

## Usage

```js
// Async request (default)
NetJS.request('https://example.com', result => {
    if (result.success) {
        console.log(result.response_code)
        console.log(result.raw_text)
    }
})

// Synchronous request
NetJS.request('https://example.com', false, result => {
    console.log(`code = ${result.response_code}`)
})

// Custom method and body
NetJS.request('https://example.com/api', 'POST', '{"test":1}', result => {
    console.log(result.raw_text)
})
```

`result.raw_text` contains the response body and `result.response_code` stores the HTTP status code. The mod performs no validation and will happily contact any address, so use it responsibly.
