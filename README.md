# Practical challenges for RxJava learners

**This project orginally forked from [sergiiz/RxBasicsKata](https://github.com/sergiiz/RxBasicsKata), added new challenges and touched with kotlin ;)** 

A set of simple code challenges to learn RxJava using JUnit tests as an acceptance criteria. Focused on some basic concepts and doesn't cover any Android topics yet.

[!["Solved" branch](https://travis-ci.com/furkanaskin/RxBasicsKataKotlin.svg?branch=solved)](https://travis-ci.com/furkanaskin/RxBasicsKataKotlin)

## Current implementation
### Dependencies:
- RxJava 2.2.8
- JUnit 4.12

### Reactive types covered:
- Observable: the heart of Rx, a class that emits a stream of data or events
- Single : a version of an Observable that emits a single item or fails
- Maybe: lazy emission pattern, can emit 1 or 0 items or an error signal

### Operators covered:
- map: transforms the items by applying a function to each item
- flatMap: takes the emissions of one Observable and returns merged emissions in another Observable to take its place
- filter: emits only those items from that pass a criteria (predicate test)
- skip/take: suppress or takes the first n items 
- all: determines whether all items meet some criteria
- reduce: applies a function to each item sequentially, and emit the final value. For example, it can be used to sum up all emitted items
- toMap: converts an Observable into another object or data structure
- merge: combine multiple Observables into one by merging their emissions
- sequenceEqual: determine whether two Observables emit the same sequence of items
- test: returns TestObserver with current Observable subscribed
- timeout: to handle timeouts, e.g. deliver some fallback data
- count: transforms an Observable that emits items into an Observable that emits a single value that represents the number of items emitted by the source Observable.
- defaultIfEmpty: simply mirrors the source Observable exactly if the source Observable emits any items. If the source Observable terminates normally (with an onComplete) without emitting any items, the Observable returned from DefaultIfEmpty will instead emit a default item of your choosing before it too completes.
- zip/zipWith: The Zip method returns an Observable that applies a function of your choosing to the combination of items emitted, in sequence, by two (or more) other Observables, with the results of this function becoming the items emitted by the returned Observable.
- concat: The Concat operator concatenates the output of multiple Observables so that they act like a single Observable, with all of the items emitted by the first Observable being emitted before any of the items emitted by the second Observable (and so forth, if there are more than two).
- scan: The Scan operator applies a function to the first item emitted by the source Observable and then emits the result of that function as its own first emission. It also feeds the result of the function back into the function along with the second item emitted by the source Observable in order to generate its second emission. It continues to feed back its own subsequent emissions along with the subsequent emissions from the source Observable in order to create the rest of its sequence.
- buffer: The Buffer operator transforms an Observable that emits items into an Observable that emits buffered collections of those items. There are a number of variants in the various language-specific implementations of Buffer that differ in how they choose which items go in which buffers.

### Testing approach:
- The set of test cases are defined in a separate java file
- As a “receiver” of emitted test events we use TestObserver. It records events and allows to make assertions about them
- All tests are failing when you just took them from the repo. This is expected behaviour. You should make tests pass by implementing the logic in [ServiceSolved](https://github.com/furkanaskin/RxBasicsKataKotlin/blob/master/app/src/main/java/com/example/rxbasicskatakotlin/ServiceSolved.kt) class

## Blog post
See my blog post at Medium for more details: https://proandroiddev.com/practical-challenges-for-rxjava-learners-1821c454de9.

## Contribution
Pull requests and new code challenges are really welcome.