
## Stock Market Project Backend 
Created for a stock market website. Created by spring and postgresql. Also in the project used a real time finance api. The project api provide real time stock infos. By the way user keep checking updated values of stocks that ownered. User can give reactions againts to value changes of stocks, can sell stock or can buy stock. Also User can see own profit or lose. Each transaction that applied by user is keeped in database as a log info, of course user can controle the own transactions by that infos. 
### API usage

#### List Stock market elements real time values.
Take stock elements infos as a real time   // Send parameters as a body

```http
  GET /stocks1
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `page` | `int` | **Pagination current page** must not be empty |
| `size` | `int` | **Page size** value must not be empty (advice take it 15) |
| `sort` | `string` | **Pagination sort element**  |
| `sortDirect` | `string` | **Sort filter** like ASC, DESC, etc..  |




#### Buy a new stocks
Add a new stocks to the ownered ones
```http
  POST /postStock
```
| Parametre | Tip     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` |  must not be empty |
| `symbol`      | `string` |  must not be empty |
| `name`      | `string` |  must not be empty |
| `exchange`      | `string` |  must not be empty |
| `type`      | `string` |  must not be empty |
| `price`      | `double` |  must not be empty |
| `quantity`      | `int` |  must not be empty |




#### List all ownered stocks
It list all ownered stocks elements
```http
  GET /getAll
```

#### Get Transaction infos
The event made in account is saved as a log and it turn the infos

```http
  GET /getHistory
```


#### Sell s stock
Sell the ownered stock.

```http
  GET /deleteStock
```

| Parametre | Tip     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` |  must not be empty |
| `symbol`      | `string` |  must not be empty |
| `name`      | `string` |  must not be empty |
| `exchange`      | `string` |  must not be empty |
| `type`      | `string` |  must not be empty |
| `price`      | `double` |  must not be empty |
| `quantity`      | `int` |  must not be empty |
| `currentPrice`      | `double` |  must not be empty |


  
## Frontend part

I'll leave it here in case you want to take a look at the front.  
[https://github.com/omerozcan-hub/staj-project-frontend-react]


  
