[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FMaKer-io%2FMaKer&count_bg=%233D7EC8&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=MaKer&edge_flat=false)](https://hits.seeyoufarm.com)
# MaKer

MaKer is a comprehensive Java library designed to enhance the functionality and efficiency of your applications. It encompasses a suite of tools for generating unique identifiers, managing event dates and times, and securing user passwords. MaKer is crafted to be both easy to integrate and flexible, catering to a wide range of development needs.

## Why Use MaKer?

Using MaKer allows for easy creation of IDs, encryption and decryption of passwords, and the effortless generation of EventDateTime classes, among many other tasks.

## Features

- **ID Generation**: Create unique IDs with customizable formats, including timestamps and random parts. Choose from various character types for the random part (Korean, English, numbers, or a mix).
- **Event Date and Time Management**: Utilize `EventDateTimeMaker` for handling complex date and time operations, ensuring your application can manage scheduling and events efficiently.
- **Password Security**: Secure user passwords with `PasswordMaker`, offering support for various encryption algorithms. This ensures that your application adheres to best practices for password security.

## Getting Started

Before you start, make sure to check the [latest version](https://central.sonatype.com/artifact/io.github.yonggoose/MaKer) of MaKer.

### Maven

If you are using Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.yonggoose</groupId>
    <artifactId>MaKer</artifactId>
    <version>0.7.5</version>
</dependency>
```


### Gradle

If you are using Gradle, you can add the following dependency to your `build.gradle`:

```groovy
dependencies {
    implementation 'io.github.yonggoose:MaKer:0.7.5'
}
```

### Usage

<details>
<summary>IDMaker</summary>

### Step 1: Add the Dependency

First, you need to add the IDMaker library as a dependency to your project. If you're using Maven or Gradle, you can do this by adding the following lines to your `pom.xml` or `build.gradle` file respectively:

**Maven:**

```xml
<dependency>
    <groupId>io.github.yonggoose</groupId>
    <artifactId>id-maker</artifactId>
    <version>0.7.5</version>
</dependency>
```

**Gradle:**

```groovy
dependencies {
    implementation 'io.github.yonggoose:id-maker:0.7.5'
}
```

### Step 2: Annotate Your Entity's ID Field

Next, you need to annotate the field in your entity that you want to generate an ID for with the `@IDMaker` annotation. You can specify the length of the random part of the ID and the type of characters to use for the random part of the ID.

Here's an example:

```java
public class MyEntity {
    @IDMaker(length = 7, type = GenerationType.EN)
    private String id;
    // other fields...
}
```

In this example, an ID will be generated for the `id` field before the `MyEntity` object is persisted. The ID will consist of a timestamp and a random English string of length 7.

### Step 3: Add Entity Listeners

You need to add the `@EntityListeners` annotation to your entity class and specify `IDMakerEntityListener.class` as the listener. This will ensure that the `prePersist` method of `IDMakerEntityListener` is called before the entity is persisted, generating a unique ID for the annotated field.

Here's how you can do it:

```java
import IDMaker.project.IDMakerEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;

@Entity
@EntityListeners(IDMakerEntityListener.class)
public class MyEntity {
    @Id
    @IDMaker(length = 7, type = GenerationType.EN)
    private String id;
    // other fields...
}
```

### Step 4: Persist Your Entity

When you persist your entity (for example, by saving it to a database), the IDMaker library will automatically generate a unique ID for the annotated field if it is `null`.

Here's an example using Spring Data JPA:

```java
MyEntity entity = new MyEntity();
// set other fields...
myEntityRepository.save(entity);
```

In this example, when you call `myEntityRepository.save(entity)`, the IDMaker library will generate a unique ID for the `id` field of `entity` before it is saved to the database.

### Step 5: Retrieve the Generated ID

After your entity has been persisted, you can retrieve the generated ID by simply calling the getter method for the annotated field.

Here's an example:

```java
String id = entity.getId();
```

In this example, `id` will contain the unique ID that was generated by the IDMaker library.

That's it! You're now using the IDMaker library to generate unique IDs for your entities.

</details>

<details>
<summary>PasswordMaker</summary>

### Step 1: Add the Dependency

Ensure that your project includes the MaKer library as a dependency. For Gradle projects, add the following line to your `build.gradle` file:

**Maven:**

```xml
<dependency>
    <groupId>io.github.yonggoose</groupId>
    <artifactId>password-maker</artifactId>
    <version>0.7.5</version>
</dependency>
```

**Gradle:**

```groovy
dependencies {
    implementation 'io.github.yonggoose:password-maker:0.7.5'
}
```

### Step 2: Annotate Your Entity's Password Field

In your entity class, use the `@PasswordMaker` annotation on the password field. This annotation allows the automatic encryption of the password when the entity is persisted. Specify the encryption type using the `encodingType` attribute.

```java
import IDMaker.passwordmaker.PasswordMaker;
import IDMaker.passwordmaker.EncodingId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;

    @PasswordMaker(encodingType = EncodingId.SCRYPT)
    private String password;

    // Constructors, getters, and setters
}
```

### Step 3: Add Entity Listeners

To enable the automatic encryption, your entity class must be annotated with `@EntityListeners`, specifying `PasswordMakerListener.class`. This ensures the encryption process is invoked before the entity is persisted.

```java
import IDMaker.passwordmaker.PasswordMakerListener;
import jakarta.persistence.EntityListeners;

@Entity
@EntityListeners(PasswordMakerListener.class)
public class User {
    // Your entity's fields and methods
}
```

### Step 4: Persist Your Entity

When you persist an entity with a password field annotated with `@PasswordMaker`, the password is automatically encrypted. This process is handled transparently, requiring no additional code for the encryption step.

```java
User user = new User();
user.setPassword("plaintextPassword");
userRepository.save(user);
```

In this example, the `plaintextPassword` is encrypted before being saved to the database.

### Step 5: Use the Encrypted Password

After encryption, the password stored in the database is in its encrypted form. You can use this encrypted password for authentication purposes, comparing it with the encrypted version of the input password during the login process.

This guide outlines the steps to integrate `PasswordMaker` into your Java application, ensuring secure password handling through encryption.

</details>

<details>
<summary>EventDateTimeMaker</summary>

### Step 1: Add the Dependency

Confirm the inclusion of the MaKer library as a dependency in your project. For Gradle-based projects, append the subsequent line to your `build.gradle` file:

**Maven:**

```xml
<dependency>
    <groupId>io.github.yonggoose</groupId>
    <artifactId>event-date-time-maker</artifactId>
    <version>0.7.5</version>
</dependency>
```

**Gradle:**

```groovy
dependencies {
    implementation 'io.github.yonggoose:event-date-time-maker:0.7.5'
}
```

### Step 2: Generate EventDateTime Instances

The `EventDateTimeMaker` module facilitates the effortless creation and management of event dates and times. Specify the desired year, month, day, hour, and minute for your event.

Example of crafting an `EventDateTime` instance:

```java
EventDateTimeArbitrary eventDateTimeArbitrary = EventDateTimeArbitrary.builder()
	.setYear(2024)
	.setMonth(7)
	.setDate(7)
	.build();
```

### Step 3: Retrieve and Utilize EventDateTime

Once you have created an `EventDateTime` instance, it can be retrieved and utilized within your application. This could be for scheduling events, comparing dates and times, or any other functionality that requires precise date and time management.

```java
EventDateTime eventDateTime = eventDateTimeArbitrary.getEventDateTime();
System.out.println("EventDateTime: " + eventDateTime);
```

</details>

## Documentation
- IDMaker :  https://devocean.sk.com/blog/techBoardDetail.do?ID=165948&boardType=techBlog#none

- PasswordMaKer : https://solution-is-here.tistory.com/213

- EventDateTimeMaKer : https://stackoverflow.com/questions/78617542/are-there-tools-like-jqwik-for-easily-testing-google-apis-time-class-datetime
## License

IDMaker is made available under the Eclipse Public License, Version 2.0 (EPL-2.0). This license allows for the use, modification, and distribution of the software, provided that such activities comply with the terms and conditions of the EPL-2.0.

## Contributing

Contributions are welcome! Feel free to submit a pull request.

## Contact

If you have any questions or issues, please open an issue on the [GitHub repository](https://github.com/IDMaker-io/IDMaker).

## Acknowledgements

- Yongjun Hong, the author of IDMaker.