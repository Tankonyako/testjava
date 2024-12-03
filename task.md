# Test Task

This is the test task to check the vision of the interviewee how the solution of the problem should look like and be ready for the production.
Flow: the request is recieved and converted into Dto.class object. The App.class prepare tree requests off String.class. 
These requests are printed into the console one by one.

## Result

The result of the test task is zipped into one archive tree folders:

-
    1) Refactored Return #1 (HTML table)
-
    2) Implemented solution Return #2 (JSON document 1)
-
    3) Implemented solution Return #3 (JSON document 2)

## Returns

-
    1) HTML table

<table>
    <tr>
        <th>Name</th>
        <th>Protection</th>
        <th>Ticker 1</th>
        <th>Ticker 2</th>
        <th>Strike date</th>
    </tr>
    <tr>
        <td>Autocall</td>
        <td>0.8</td>
        <td>AAPL_UQ</td>
        <td>TSLQ_US</td>
        <td>09-07-2024</td>
    </tr>
</table>

-
    2) JSON document 1

```json
{
  "name": "Autocallable",
  "barrier": 0.8,
  "tickers": [
    "AAPL_UQ",
    "TSLQ_US"
  ],
  "strikeDate": "09-07-2024"
}
```

-
    3) JSON document 2

```json
{
  "title": "Autocall",
  "protetion": 80,
  "tickers": [
    {
      "name": "AAPL_UQ",
      "strikeDate": "09-07-2024"
    },
    {
      "name": "TSLQ_US",
      "strikeDate": "09-07-2024"
    }
  ]
}
```  