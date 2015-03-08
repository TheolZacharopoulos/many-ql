package graphic

import (
	"bytes"
	"text/template"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/stylelang"
)

const tabsTemplate = `
	Tab {
		title: "{{ .Name }}"
		objectName: "{{ .Name }}"
		width: 798
		height: 600
		Layout.fillHeight: true
		active: true
		ScrollView {
			objectName: "scroll"
			width: 798
			height: 400
			verticalScrollBarPolicy: Qt.ScrollBarAlwaysOn
			contentItem: ColumnLayout {
				Layout.fillHeight: true
				width: 797
				objectName: "questions"
				{{ .NestedPages }}
			}
		}
	}
`

func drawTab(name, nestedPages string) string {
	var qml bytes.Buffer
	t := template.Must(template.New("tab").Parse(tabsTemplate))
	t.Execute(&qml, struct {
		Name        string
		NestedPages string
	}{name, nestedPages})
	return qml.String()
}

const tabsViewTemplate = `
TabView {
	width: 799
	height: 600
	objectName: "{{ .TabName }}View"
	Layout.fillHeight: true
	Layout.fillWidth: true

	{{ .Tabs }}
}
`

func drawTabBlock(page *stylelang.Page) string {
	nestedTabBlock := ""
	if page.HasNestedPages() {
		ps := page.Pages()
		for _, pageIdx := range page.PagesOrder() {
			p := ps[pageIdx]
			nestedPages := ""
			if p.HasNestedPages() {
				nestedPages = drawTabBlock(p)
			}
			tmp := nestedTabBlock + drawTab(p.Name(), nestedPages)
			nestedTabBlock = tmp
		}
	} else {
		nestedTabBlock = drawTab(page.Name(), "")
	}

	var qml bytes.Buffer
	t := template.Must(template.New("tabView").Parse(tabsViewTemplate))
	t.Execute(&qml, struct {
		TabName string
		Tabs    string
	}{page.Name(), nestedTabBlock})
	return qml.String()
}
