/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { PmDetailComponent } from 'app/entities/pm/pm-detail.component';
import { Pm } from 'app/shared/model/pm.model';

describe('Component Tests', () => {
    describe('Pm Management Detail Component', () => {
        let comp: PmDetailComponent;
        let fixture: ComponentFixture<PmDetailComponent>;
        const route = ({ data: of({ pm: new Pm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [PmDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PmDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PmDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pm).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
