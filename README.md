Тестовое задание для обучающего курса. ТЗ:

В приложении должно быть 2 вкладки.
В каждой вкладке должен отображаться список элементов
У табов должны быть выставлены названия: "Факты из интернета", "Избранное"
На первой вкладке должен отображаться список фактов загруженных из API. На второй вкладке отображается список фактов, которые пользователь добавил в избранное.
Список избранного должен отображаться даже в случае отсутствия доступа к интернету. В случае добавления/удаления факта из избранного список должен актуализироваться без дополнительных действий от пользователя (например без перезапуска прилоежния)
Нажатие на элемент в обоих списках ведет на одинаковый экран - детальное отображение факта
На экране детального отображения факта должены отображаться следующие элементы:
- текст факта
- картинка со случайным изображением кошки
- кнопка добавления/удаления в избранное
Они должны быть расположены вертикально один под другим
Текст факта должен отображаться полностью без обрезания и наложения на прочие элементы. Расстояние между текстом и картинкой должно оставаться одинаковым вне зависимости от длины текста
Для получения картинки нужно сначала получить адрес случайной картинки из апи
https://aws.random.cat/meow  
Дальше по полученному адресу нужно получить изображение и установить его в соответствующий компонент. До загрузки изображения должен отображаться индикатор загрузки.
Изображение должно сохрянять пропорции (не должно сжиматься или растягиваться), но может обрезаться. Так же оно не должно накладываться на другие элементы. Изображение должно занимать все пустое пространство между текстом и кнопкой
Кнопка добавления в избранное должна менять свое состояние. На ней должно быть написано:
- если факт не добавлен в избранное - "Добавить в избранное"
- если факт добавлен в избранное - "Удалить из избранного"
Кнопка должна находиться по центру внизу экрана и должна быть видна на экранах любого размера
