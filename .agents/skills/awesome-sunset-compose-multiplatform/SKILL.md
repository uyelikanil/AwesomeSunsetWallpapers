---
name: awesome-sunset-compose-multiplatform
description: Treat AwesomeSunsetWallpapers as a Compose Multiplatform project targeting Android and iOS. Apply its source-set and platform conventions together with pragmatic clean architecture, Fowler-style evolutionary design, human-readable code, minimal boilerplate, and resistance to over-engineering. Use for any coding, UI, navigation, dependency, Gradle, testing, refactoring, architecture, build, release, Android, or iOS task in this repository.
---

# Awesome Sunset Compose Multiplatform

Treat Android and iOS as equal application targets. Keep behavior shared unless a
platform API genuinely requires a target-specific implementation.

## Design for People

Apply pragmatic clean architecture with an evolutionary-design mindset:

- Keep domain and application rules independent from UI, frameworks, storage,
  networking, and native platform APIs.
- Point dependencies inward, but treat boundaries as dependency rules rather
  than a reason to create more files and layers.
- Prefer the simplest design that clearly solves the current requirement.
- Refactor in small, safe steps. Introduce abstractions after a real variation,
  reuse case, testing seam, or dependency boundary appears.
- Optimize for the next human reader. Use precise names, short cohesive
  functions, explicit data flow, and unsurprising control flow.
- Make invalid states difficult to represent when doing so remains simple.
- Keep comments for intent, trade-offs, or platform constraints; make the code
  explain mechanics.

Do not create architecture theatre. Avoid:

- one-line pass-through use cases, repositories, managers, wrappers, or mappers;
- interfaces with only one implementation and no boundary or testing value;
- generic base classes, premature extension points, and speculative reuse;
- duplicate DTO/domain/UI models when the models have no semantic difference;
- a new state holder, event type, sealed hierarchy, or module for trivial local
  behavior;
- splitting a readable file merely to satisfy an arbitrary size rule;
- clever abstractions that hide straightforward Compose or Kotlin code.

Reuse an existing project pattern when it adds consistency, but do not multiply
ceremony mechanically. If the existing architecture would require boilerplate
with no value for the requested change, keep the implementation local and
explain the deliberate simplification.

## Establish Context First

Before changing code:

1. Inspect the affected module's `build.gradle.kts` and sibling source sets.
2. Inspect the relevant convention plugin under `build-logic/convention`.
3. Check `gradle/libs.versions.toml` before adding or changing dependencies.
4. Classify the task as shared, Android-only, iOS-only, or shared with platform
   adapters.

Do not infer that an Android-looking request is Android-only when it changes
shared UI, state, navigation, data, domain logic, resources, or dependency
wiring.

## Project Shape

- Targets: `androidTarget()`, `iosArm64()`, and `iosSimulatorArm64()`.
- `composeApp/src/commonMain` owns the shared app UI, navigation, and Koin setup.
- `composeApp/src/androidMain` owns the Android application and activity.
- `composeApp/src/iosMain` exposes the Compose `MainViewController`.
- `iosApp` is the native SwiftUI host and owns Xcode project settings.
- `core/*` and `feature/*` are primarily Kotlin Multiplatform modules configured
  through convention plugins.
- Shared tests belong in `commonTest` unless the behavior is platform-specific.

## Choose the Correct Source Set

- Put Compose UI, view models, navigation, business logic, models, repositories,
  and platform-neutral interfaces in `commonMain` by default.
- Put Android framework APIs and Android-only implementations in `androidMain`.
- Put Apple APIs and iOS-only implementations in `iosMain`.
- Follow the module's existing abstraction style. This project uses both
  `expect`/`actual` and shared interfaces with platform implementations wired
  through Koin.
- When shared behavior needs native capabilities, define the seam in
  `commonMain`, implement every required target, and wire each platform module.
- Prefer `commonTest` for shared behavior and reuse `:core:testing` test doubles.

Never import Android framework classes or Android-only artifacts into
`commonMain`. Do not solve a shared feature by silently implementing only the
Android path.

## Dependencies, Resources, and Build Logic

- Reuse version-catalog aliases from `gradle/libs.versions.toml`.
- Use JetBrains Compose and JetBrains AndroidX multiplatform artifacts in
  `commonMain`; keep AndroidX Android-only artifacts in `androidMain`.
- Reuse the existing convention plugins instead of duplicating target,
  Compose, Android, or Koin configuration in individual modules.
- Put shared Compose resources in `core/resource` and access them through the
  generated multiplatform resource class. Do not introduce Android `R` usage
  into shared UI.
- Keep shared Koin modules in `commonMain`; pass platform modules from Android
  and iOS entry points.
- Keep Swift/SwiftUI code in `iosApp` for native host concerns and integrations
  that must live on the Apple side.

When another skill gives Android-specific advice, apply it only to the Android
target and reconcile it with these multiplatform boundaries.

## Implementation Workflow

1. Inspect equivalent Android and iOS implementations before editing a shared
   contract.
2. Trace the actual data and dependency flow before adding a new layer.
3. Make the smallest change in the highest valid shared source set.
4. Add platform-specific code only for irreducible native behavior.
5. Preserve API and behavior parity across targets unless the user explicitly
   requests a platform-only feature.
6. Remove incidental duplication and boilerplate introduced by the change.
7. Read the final diff as a maintainer: simplify names, branching, indirection,
   and file boundaries before adding explanatory comments.
8. Verify both targets for shared changes and report any target not verified.

## Validation

This project requires Java 21. When the shell uses an older JDK, run Gradle with:

```bash
JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home" ./gradlew <tasks>
```

Use focused tasks proportional to the change:

```bash
./gradlew :composeApp:compileDebugKotlinAndroid
./gradlew :composeApp:compileKotlinIosSimulatorArm64
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

Run the affected module's Android unit tests and `iosSimulatorArm64Test` when
shared tested behavior changes. An Android-only compile is not sufficient
validation for a `commonMain` change.

For native iOS host or Xcode-setting changes, use the Xcode app toolchain:

```bash
DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer xcodebuild \
  -project iosApp/awesomesunsetwallpapers.xcodeproj \
  -scheme awesomesunsetwallpapers \
  -configuration Debug \
  -destination 'generic/platform=iOS Simulator' \
  ARCHS=arm64 CODE_SIGNING_ALLOWED=NO build
```
