package main

import (
	"fmt"
	"net/http"
	//"net/http/httputil"
	"encoding/json"
	"io/ioutil"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/postgres"
)

type Raid struct {
	Id   int    `gorm:"AUTO_INCREMENT"  json:"id"`
	Name string `json:"name"`
}

var dbVendor = "postgres"

//var dbHost = "172.17.0.2"
var dbHost = "postgres"
var dbName = "postgres"
var dbUser = "postgres"
var dbPw = "ilovepostgres"

func main() {
	fmt.Printf("hello,go\n")

	db, err := gorm.Open("postgres", "host=postgres user=postgres dbname=postgres sslmode=disable password=ilovepostgres")
	if err != nil {
		panic("failed to connect database")
	}
	defer db.Close()

	db.AutoMigrate(&Raid{})

	http.HandleFunc("/earth/", handler)
	http.HandleFunc("/saveRaid/", saveRaid)
	http.HandleFunc("/getAllRaids/", getAllRaids)
	http.HandleFunc("/getRaidByName/", getRaidByName)
	http.ListenAndServe(":8080", nil)
}

func handler(w http.ResponseWriter, r *http.Request) {
	fmt.Print(r.URL.Path[7:])
	fmt.Fprintf(w, "Hello Earth\n", r.URL.Path)
}

func saveRaid(w http.ResponseWriter, r *http.Request) {
	//	fmt.Fprint(w,"post handler called ")
	//	bytes, _ := httputil.DumpRequestOut(r, true)
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		panic(err)
	}
	fmt.Print("Rescived Body:", string(body))
	persistRaid(string(body))

	//fmt.Fprintf(w,string(body))
	//fmt.Fprintf(w,string(bytes))
	//fmt.Fprint(w," the body is ",)
	fmt.Fprint(w, "Raid "+string(body)+" was saved")

}

func persistRaid(name string) {

	db, err := gorm.Open(dbVendor, "host="+dbHost+" user="+dbUser+" dbname="+dbName+" sslmode=disable password="+dbPw)
	if err != nil {
		panic("failed to connect database")
	}
	defer db.Close()
	//        raidiii := Raid{Name :"Love you"}
	raid := Raid{Id: 0, Name: name}
	fmt.Printf("Save Raid: ", raid.Name)
	fmt.Printf("%d", raid.Id)

	//db.AutoMigrate(&Raid{})

	db.NewRecord(raid)
	db.Create(&raid)
}

func getAllRaids(w http.ResponseWriter, r *http.Request) {
	db, err := gorm.Open(dbVendor, "host="+dbHost+" user="+dbUser+" dbname="+dbName+" sslmode=disable password="+dbPw)
	if err != nil {
		panic("failed to connect database")
	}
	defer db.Close()
	raids := []Raid{}
	db.Find(&raids)
	fmt.Println("get All Raids called")
	jsonRaids, err := json.Marshal(raids)
	if err != nil {
		panic("json conversion did not work")
	}
	fmt.Fprintf(w, string(jsonRaids))
	//return string(jsonRaids)

}

func getRaidByName(w http.ResponseWriter, r *http.Request) {
	db, err := gorm.Open(dbVendor, "host="+dbHost+" user="+dbUser+" dbname="+dbName+" sslmode=disable password="+dbPw)
	if err != nil {
		panic("failed to connect database")
	}
	defer db.Close()
	raid := []Raid{}
	//raid := Raid{}
	name := r.URL.Path[15:]
	name = name[:len(name)-1]
	fmt.Println("url path name:", name)
	db.Where("Name = ?", name).Find(&raid)
	//db.Find(&raid, "name = ?", "yo")
	jsonRaid, err := json.Marshal(raid)
	if err != nil {
		panic("json conversion failed")
	}

	//fmt.Println("found", raid[0].Name)
	//jsonString := raidsToJson(raid)
	fmt.Println(string(jsonRaid))
	fmt.Fprint(w, string(jsonRaid))
	// fmt.Fprintf(w, Raid)
	//	fmt.Fprint(w, raid)
}

func getRaidByID(ID int) string {
	db, err := gorm.Open(dbVendor, "host="+dbHost+" user="+dbUser+" dbname="+dbName+" sslmode=disable password="+dbPw)
	if err != nil {
		panic("failed to connect database")
	}
	defer db.Close()
	raid := Raid{}
	db.First(&raid, ID)
	jsonRaid, err := json.Marshal(raid)
	if err != nil {
		panic("json conversion did not work")
	}
	return string(jsonRaid)

}

/*func raidsToJson(raids []Raid) string {
	//jraid := []JsonRaid{}
	var slice = make([]JsonRaid, len(raids))

	for i := 0; i < len(raids); i++ {

		slice[i].name = raids[i].Name
		slice[i].id = raids[i].Id
	}

	fmt.Println("After change", slice)
	jsonBytes, err := json.Marshal(slice)
	if err != nil {
		panic("json cconversion failed")
	}
	fmt.Println(jsonBytes)
	return string(jsonBytes)
}*/
