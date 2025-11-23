# Pydroid-to-NanoHTTPD Clipboard Bridge

### This project aims to solve pain points and acquire skills in the following areas:

* Cross-domain thinking (Python + Android + HTTP + clipboard APIs)
* Problem-solving under constraints (sandboxing, no root, no direct clipboard access)
* Empathetic engineering (solving a real pain point for others, not just for ourself)
* Minimalist glueware design (no bloated dependencies, just smart bridging)

*This shows that we don't just code. We engineer solutions.*

---

### 🧾 Project Title: 
### Clipboard Bridge: Pydroid to Android via NanoHTTPD (Nov 2025)

#### Description:

A lightweight solution to bypass clipboard limitations in sandboxed Android environments like _**"Pydroid"**_ app. This project uses a local HTTP server (NanoHTTPD) running in a foreground service to receive POST requests from Python scripts inside _Pydroid_ app and copy data to the Android clipboard. Designed to help users working in constrained environments where direct clipboard access is restricted.

#### Highlights:

* Built a foreground service with NanoHTTPD to persist across activity switches and backgrounding
* Enabled Python-to-Android communication using requests.post() and JSON payloads
* Implemented safe clipboard access with lifecycle-aware service design
* Inspired by real-world community pain points and designed for ethical, practical use
* Demonstrates Android internals knowledge, cross-language integration, and user-centered design
