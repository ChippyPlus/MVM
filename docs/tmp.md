| Feature | lateinit |
lazy | |----------------|-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - | - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - | |
Mutability | var (mutable) | val (read-only) | | Nullability | Non-null only | Inferred from initializer | | Thread
Safety | Not thread-safe | Thread-safe | | Initialization | Imperative, before first access | On first access, result is
cached | | Use Cases | External initialization, frameworks | Expensive computations, on-demand |
