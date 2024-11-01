Install: https://plugins.jetbrains.com/plugin/25675-suppressed-attributes

This plugin makes it possible to suppress the "Unused declaration" inspection on methods or fields that have a specific attributes that the user can choose.
The user can specify a list of the attributes considered.
This is similar to the "Suppressed annotations" feature in PhpStorm, but for attributes.

The user specifies an attribute by supplying its fully qualified name (FQN). The supplied FQN Should start with a backslash (\).
Example: \Fully\Qualified\Name\SuppressingAttribute

The user might need to reopen the files that should be affected if no result is immediately visible.
