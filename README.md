# Json Table Generator
Generate Markdown Table From JSON Data

## How to use
Assume that you have a file data.json

`cat data.json`

```json

[
	{
		"field1": "hello",
		"field2": "world"
	}, {
		"field1": "this is",
		"field2": "an example"
	}
]
```

### Build

```
gradle build
```

### Generate 

Generating markdown table from json data 

```
gradle gen -Pdata=data.json 
```

you will see:

```
|field1|field2|
|:---:|:---:|
|hello|world|
|this is|an example|

```

### Preview

|field1|field2|
|:---:|:---:|
|hello|world|
|this is|an example|
